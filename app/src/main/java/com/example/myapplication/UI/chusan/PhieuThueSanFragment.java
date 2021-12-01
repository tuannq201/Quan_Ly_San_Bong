package com.example.myapplication.UI.chusan;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.adapter.RecyclerTenSanTableAdapter;
import com.example.myapplication.adapter.SpinnerCumSanAdapter;
import com.example.myapplication.adapter.nguoi_thue_adapter.DemoAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.util.Cover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PhieuThueSanFragment extends Fragment {

    RecyclerView rcvTenSan;
    SanDAO sanDAO;
    CumSanDAO cumSanDAO;
    Spinner spnChonSan;
    List<San> listSan;
    RecyclerTenSanTableAdapter adapter;
    San san;
    List<CumSan> listCumSan;
//    int maCumSan, maCumSanHienTai;
    SpinnerCumSanAdapter spinnerCumSanAdapter;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_FOLDER = 1;
    private int position;
    int soSan = 0;
    String phone;
    PhieuThueDAO phieuThueDAO;
    List<TrangThai> trangThais;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    GridView gridView;
    DemoAdapter demoAdapter;
    int maCumSan = 7;
    public PhieuThueSanFragment() {
    }


    public static PhieuThueSanFragment newInstance() {
        PhieuThueSanFragment fragment = new PhieuThueSanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        phone = pref.getString("PHONE","");
        phieuThueDAO = new PhieuThueDAO(getContext());
        trangThais = new ArrayList<>();
        sanDAO = new SanDAO(getContext());
        listSan = sanDAO.getSanByCumSan(String.valueOf(maCumSan));
        soSan = listSan.size();
        Date now = new Date();
        String strNow = format.format(now);
        for (int i = 1;i<= 12;i++){
            for (int y = 1;y<=soSan;y++){
                int maSan = listSan.get(y-1).maSan;
                String ca = String.valueOf(i);
                TrangThai trangThai = phieuThueDAO.checkTrangThai(maSan, ca, strNow);
                trangThais.add(trangThai);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phieu_thue_san, container, false);
        rcvTenSan = view.findViewById(R.id.recycler_ten_san);
        sanDAO = new SanDAO(getActivity());
        cumSanDAO = new CumSanDAO(getContext());
        spnChonSan = view.findViewById(R.id.spn_chonCumSan);
        listCumSan = new ArrayList<>();
        listCumSan = cumSanDAO.getCSByChuSan(phone);
//        setSpinner();
        gridView = view.findViewById(R.id.nt_gridview);
        gridView.setNumColumns(soSan);


        demoAdapter = new DemoAdapter(getContext(), (ArrayList<TrangThai>) trangThais);
        gridView.setAdapter(demoAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int ca = i/soSan + 1;
                int ngay = i%soSan;
                String san = listSan.get(ngay).tenSan;
                String tt = "trống";
                if(trangThais.get(i).taiKhoan.contains("0")){
                    tt = "đã thuê";
                }
                Toast.makeText(getContext(), tt+" ca:"+ca+" sân:"+san+" tiền sân:"+ Cover.IntegerToVnd(trangThais.get(i).tienSan)+"vnd", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

//    private void setSpinner() {
//        listSan = new ArrayList<>();
//        spinnerCumSanAdapter = new SpinnerCumSanAdapter(getContext(), (ArrayList<CumSan>) listCumSan);
//        spnChonSan.setAdapter(spinnerCumSanAdapter);
//        spnChonSan.setDropDownVerticalOffset(70);
//        spnChonSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                maCumSanHienTai = listCumSan.get(position).maCumSan;
//                Toast.makeText(getContext(), ""+maCumSanHienTai, Toast.LENGTH_SHORT).show();
//                listSan = sanDAO.getSanByCumSan(String.valueOf(maCumSanHienTai));
//                updateLV(listSan);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }

    public void updateLV(List<San> sanList){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rcvTenSan.setLayoutManager(mLayoutManager);
        adapter = new RecyclerTenSanTableAdapter(getActivity(), sanList, new ITFOnItenClick() {
            @Override
            public void onItemClick(San san, int type) {
            }

            @Override
            public void onItemClick(PhieuThue phieuThue) {
            }

            @Override
            public void onItemClick(CumSan cumSan, int type) {
            }
        });
        adapter.notifyDataSetChanged();
        rcvTenSan.setAdapter(adapter);

    }
//    private void openDialog(int type, San san1) {
//        Dialog dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.dialog_add_san);
//        Window window = dialog.getWindow();
//        window.getAttributes().windowAnimations = R.style.DialogAnimation;
//        spTenCumSan = dialog.findViewById(R.id.sp_tenCumSan);
//        edTenSan = dialog.findViewById(R.id.ed_ten_san);
//        edLoaiSan = dialog.findViewById(R.id.ed_loai_san);
//        edGiaSan = dialog.findViewById(R.id.ed_gia_san);
//        imgSan = dialog.findViewById(R.id.image_san);
//        btCamera = dialog.findViewById(R.id.btn_camera);
//        btAlbum = dialog.findViewById(R.id.btn_albuml);
//        btSave = dialog.findViewById(R.id.btn_save);
//
//        btCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
//            }
//        });
//        btAlbum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_CODE_FOLDER);
//            }
//        });
//        CumSanDAO cumSanDAO = new CumSanDAO(getContext());
//        listCumSan = new ArrayList<>();
//        listCumSan = cumSanDAO.getAll();
//        spinnerCumSanAdapter = new SpinnerCumSanAdapter(getContext(), (ArrayList<CumSan>) listCumSan);
//        spTenCumSan.setAdapter(spinnerCumSanAdapter);
//        spTenCumSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                maCumSan = listCumSan.get(position).maCumSan;
//                String tenCumSan = listCumSan.get(position).tenCumSan;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        if (type != 0){
//            edTenSan.setText(san1.tenSan);
//            edLoaiSan.setText(san1.loaiSan);
//            edGiaSan.setText(""+san1.giaSan);
//            btSave.setText("Cập Nhật");
//            for (int i=0;i<listCumSan.size();i++){
//                if (san1.maCumSan == (listCumSan.get(i).maCumSan)){
//                    position = i;
//                }
//            }
//            spTenCumSan.setSelection(position);
//        }
//        btSave.setOnClickListener(v -> {
//            if (validate()){
//                dao = new SanDAO(getContext());
//                String tenSan = edTenSan.getText().toString().trim();
//                String loaiSan = edLoaiSan.getText().toString().trim();
//                int giaSan = Integer.parseInt(edGiaSan.getText().toString().trim());
//                san = new San();
//                san.tenSan = tenSan;
//                san.loaiSan = loaiSan;
//                san.giaSan = giaSan;
//                san.anhSan = Cover.imageViewToByteArray(imgSan);
//                san.maCumSan = maCumSan;
//                if (type ==0){
//                    if (dao.insert(san) > 0){
//                        Toast.makeText(getContext(), "Tạo sân thành công", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(getContext(), "Tạo sân không thành công", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    san.maSan = san1.maSan;
//                    if (dao.update(san) > 0){
//                        Toast.makeText(getContext(), "Sửa sân thành công", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(getContext(), "Sửa sân không thành công", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                if (maCumSan == maCumSanHienTai){
//                    listSan = dao.getSanByCumSan(String.valueOf(maCumSan));
//                }
//                updateLV(listSan);
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }

//    private void onClickGoToCaSan(San san) {
//        Intent intent = new Intent(this.getContext(), CaSanOfChuSanActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("object_san", san);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK && data != null){
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            imgSan.setImageBitmap(bitmap);
//        }
//        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data != null){
//            Uri uri = data.getData();
//            imgSan.setImageURI(uri);
//        }
//    }
//
//    public boolean validate(){
//        boolean check = true;
//        if (edTenSan.getText().length() == 0 || edLoaiSan.getText().length() == 0|| edGiaSan.getText().length() == 0){
//            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//            check = false;
//        }
//        return check;
//    }
//    public void delete(String id) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Xóa Sân");
//        builder.setMessage("Bạn có muốn xóa Không ?");
//        builder.setCancelable(true);
//        builder.setPositiveButton("Có", (dialog, which) ->{
//            dao.delete(id);
//            if (maCumSan == maCumSanHienTai){
//                listSan = dao.getSanByCumSan(String.valueOf(maCumSan));
//            }
//            Log.i("======",""+listSan.size());
//            updateLV(listSan);
//            dialog.cancel();
//        } );
//        builder.setNegativeButton("Không", (dialog, which) ->{
//            dialog.cancel();
//        });
//        AlertDialog alertDialog = builder.create();
//        builder.show();
//    }
}