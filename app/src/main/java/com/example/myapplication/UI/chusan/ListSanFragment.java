package com.example.myapplication.UI.chusan;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.adapter.SpinnerCumSanAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.util.Cover;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListSanFragment extends Fragment {
    Fragment fragment;
    RecyclerView rcvSan;
    SanDAO dao;
    List<San> listSan;
    ListSanAdapter adapter;
    San san;
    EditText edTenSan, edLoaiSan, edGiaSan;
    Spinner spTenCumSan;
    Button btSave, btAlbum, btCamera;
    ImageView imgSan;
    List<CumSan> listCumSan;
    int maCumSan;
    SpinnerCumSanAdapter spinnerCumSanAdapter;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_FOLDER = 1;
    private int position;

    public ListSanFragment() {
    }

    public static ListSanFragment newInstance(String param1, String param2) {
        ListSanFragment fragment = new ListSanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_san, container, false);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            openDialog(0, san);
        });
        rcvSan = view.findViewById(R.id.list_san_chu_san);
        dao = new SanDAO(getActivity());
        updateLV();
        return view;
    }
    public void updateLV(){
        listSan = (ArrayList<San>) dao.getAll();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvSan.setLayoutManager(mLayoutManager);
        rcvSan.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListSanAdapter(getActivity(), listSan, new ITFOnItenClick() {
            @Override
            public void onItemClick(San san, int type) {
                if (type ==0){
                    onClickGoToCaSan(san);
                }else {
                    openDialog(1, san);
                }
            }

            @Override
            public void onItemClick(PhieuThue phieuThue) {
            }
        });
        rcvSan.setAdapter(adapter);
    }

    private void openDialog(int type, San san1) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_san);
        Window window = dialog.getWindow();
        window.getAttributes().windowAnimations = R.style.DialogAnimation;
        spTenCumSan = dialog.findViewById(R.id.sp_tenCumSan);
        edTenSan = dialog.findViewById(R.id.ed_ten_san);
        edLoaiSan = dialog.findViewById(R.id.ed_loai_san);
        edGiaSan = dialog.findViewById(R.id.ed_gia_san);
        imgSan = dialog.findViewById(R.id.image_san);
        btCamera = dialog.findViewById(R.id.btn_camera);
        btAlbum = dialog.findViewById(R.id.btn_albuml);
        btSave = dialog.findViewById(R.id.btn_save);

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String phone = pref.getString("PHONE","");


        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        btAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        CumSanDAO cumSanDAO = new CumSanDAO(getContext());
        listCumSan = new ArrayList<>();
        listCumSan = cumSanDAO.getAll();
        spinnerCumSanAdapter = new SpinnerCumSanAdapter(getContext(), (ArrayList<CumSan>) listCumSan);
        spTenCumSan.setAdapter(spinnerCumSanAdapter);
        spTenCumSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maCumSan = listCumSan.get(position).maCumSan;
                String tenCumSan = listCumSan.get(position).tenCumSan;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (type != 0){
            edTenSan.setText(san1.tenSan);
            edLoaiSan.setText(san1.loaiSan);
            edGiaSan.setText(""+san1.giaSan + "VND");
            for (int i=0;i<listCumSan.size();i++){
                if (san1.maCumSan == (listCumSan.get(i).maCumSan)){
                    position = i;
                }
            }
            spTenCumSan.setSelection(position);
        }
        btSave.setOnClickListener(v -> {
            if (validate()){
                dao = new SanDAO(getContext());
                String tenSan = edTenSan.getText().toString().trim();
                String loaiSan = edLoaiSan.getText().toString().trim();
                int giaSan = Integer.parseInt(edGiaSan.getText().toString().trim());
                san = new San();
                san.tenSan = tenSan;
                san.loaiSan = loaiSan;
                san.giaSan = giaSan;
                san.anhSan = Cover.imageViewToByteArray(imgSan);
                san.taiKhoan = phone;
                san.maCumSan = maCumSan;
                if (type ==0){
                    if (dao.insert(san) > 0){
                        Toast.makeText(getContext(), "Tạo sân thành công", Toast.LENGTH_SHORT).show();
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_container,new ListSanFragment());
//                        transaction.commit();
                    }else {
                        Toast.makeText(getContext(), "Tạo sân không thành công", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (dao.update(san) > 0){
                        Toast.makeText(getContext(), "Sửa sân thành công", Toast.LENGTH_SHORT).show();
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_container,new ListSanFragment());
//                        transaction.commit();
                    }else {
                        Toast.makeText(getContext(), "Sửa sân không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                updateLV();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void onClickGoToCaSan(San san) {
        Intent intent = new Intent(this.getContext(), CaSanOfChuSanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_san", san);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgSan.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data != null){
            Uri uri = data.getData();
            imgSan.setImageURI(uri);
        }
    }

    public boolean validate(){
        boolean check = true;
        if (edTenSan.getText().length() == 0 || edLoaiSan.getText().length() == 0|| edGiaSan.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = false;
        }
        return check;
    }
}