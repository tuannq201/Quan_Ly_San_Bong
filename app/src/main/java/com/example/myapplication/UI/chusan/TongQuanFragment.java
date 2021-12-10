package com.example.myapplication.UI.chusan;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ADadapter.ChuSanAdapter;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CaSanAdapter;
import com.example.myapplication.adapter.SpinnerCumSanAdapter;
import com.example.myapplication.adapter.SpinnerSanAdapter;
import com.example.myapplication.adapter.TongQuanAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.Cover;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TongQuanFragment extends Fragment {

    TextView tvNgay, btnChonNgay,tvTongNgay;
    ListView lvTQ;
    ArrayList<TrangThai> list = new ArrayList<>();
    PhieuThueDAO phieuThueDAO;
    SanDAO sanDAO;
    Date now;
    String ngay = "";
    SimpleDateFormat formatNgay = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatGio = new SimpleDateFormat("HH:mm");
    int posNow = 0;
    TrangThai item;
    Spinner spCum,spSan;
    TongQuanAdapter tongQuanAdapter;
    SpinnerCumSanAdapter spinnerCumSanAdapter;
    SpinnerSanAdapter spinnerSanAdapter;
    int maCumSan, maSan;
    int maSanHienTai;
    int maCumSanHienTai;
    List<CumSan> listCumSan;
    List<San> listSan;
    String phone;
    Dialog dialog;
    Button btnGiuSanDialog, btnHuy;
    TextView edGioThueDialog,edCaThueDialog,edTrangThaiDialog,edGiaThueDialog, tvKhuyenMaiDialog;
    EditText edKhuyenMaiDialog;

    public TongQuanFragment() {
    }


    public static TongQuanFragment newInstance(San san, String type ) {
        TongQuanFragment fragment = new TongQuanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        phone = pref.getString("PHONE","");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tong_quan, container, false);
        phieuThueDAO = new PhieuThueDAO(getContext());
        lvTQ = view.findViewById(R.id.lvTQ);
        tvNgay = view.findViewById(R.id.tvNgayTQ);
        tvTongNgay = view.findViewById(R.id.tvTongNgay);
        btnChonNgay = view.findViewById(R.id.btnNgayTQ);
        spCum = view.findViewById(R.id.spCumSan);
        spSan = view.findViewById(R.id.spSan);
        now = new Date();
        ngay = formatNgay.format(now);
        posNow = Cover.dateToPos(formatNgay.format(now), "", 1);
        tvNgay.setText(formatNgay.format(now));

        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay();
            }
        });
        CumSanDAO cumSanDAO = new CumSanDAO(getContext());
        listCumSan = new ArrayList<>();
        listCumSan = cumSanDAO.getCSByChuSan(phone);
        spinnerCumSanAdapter = new SpinnerCumSanAdapter(getContext(), (ArrayList<CumSan>) listCumSan);
        spCum.setAdapter(spinnerCumSanAdapter);
        spCum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maCumSan = listCumSan.get(position).maCumSan;
                String tenCumSan = listCumSan.get(position).tenCumSan;
                maCumSanHienTai = listCumSan.get(position).maCumSan;
//                Log.e("//", "onItemSelected: "+maCumSanHienTai);
                updateSpinnerSan(String.valueOf(maCumSanHienTai));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        lvTQ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.add(Calendar.DAY_OF_YEAR, +7);
                int posAdd7 = Cover.dateToPos(formatNgay.format(calendar.getTime()), "", 1);

                if (Cover.dateToPos(ngay, "", 1) < posNow){
                    Toast.makeText(getContext(), "Vui lòng chọn ngày hôm nay trở về sau để quản lý!!!", Toast.LENGTH_SHORT).show();
                    tvNgay.setText(ngay);
                    setCaSan(ngay);
                    lvTQ.setClickable(false);
                }else {
                    item = list.get(i);
                    openDialog(i+1);
                }

            }
        });
        return view;
    }
    public void updateSpinnerSan(String maCumSanChonSan){
        sanDAO = new SanDAO(getContext());
        listSan = new ArrayList<>();
        listSan = sanDAO.getSanByCumSan(String.valueOf(maCumSanChonSan));
        spinnerSanAdapter = new SpinnerSanAdapter(getContext(), (ArrayList<San>) listSan);
        spSan.setAdapter(spinnerSanAdapter);
        spSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSan = listSan.get(position).maSan;
                maSanHienTai = maSan;
                String tenSan = listSan.get(position).tenSan;
                setCaSan(ngay);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void setCaSan(String ngay){
        list.clear();
        for (int i = 1; i <= 12 ; i++) {
            item = phieuThueDAO.checkTrangThai(maSanHienTai, String.valueOf(i), ngay);
            if (item.taiKhoan.equals(phone)){
                item.taiKhoan = "Giữ sân";
            }
            list.add(item);
        }
        ThuNhapNgay();
        tongQuanAdapter = new TongQuanAdapter(getContext(), list);
        lvTQ.setAdapter(tongQuanAdapter);
    }
    public void chonNgay(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                tvNgay.setText(Cover.formater(y,m,d));
                ngay = Cover.formater(y,m,d);
                setCaSan(Cover.formater(y,m,d));

            }
        },year, month, day);
        datePickerDialog.show();
    }
    public void ThuNhapNgay(){
        int tongNgay=0;
        for (int i = 0; i < list.size(); i++) {
            item = list.get(i);
            int tien = item.tienSan;
            String tt = item.taiKhoan;
            if (tt.contains("0")){
                tongNgay = tongNgay + tien;
            }
        }
        tvTongNgay.setText("Tổng thu nhập ngày: "+Cover.IntegerToVnd(tongNgay)+" VNĐ");
    }
    private void openDialog(int ca){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.ca_san_dialog);
        edGioThueDialog = dialog.findViewById(R.id.tvGioThueDialog);
        edGiaThueDialog = dialog.findViewById(R.id.tvGiaThueDialog);
        edCaThueDialog = dialog.findViewById(R.id.tvCaThueDialog);
        edTrangThaiDialog = dialog.findViewById(R.id.tvTrangThaiDialog);
        tvKhuyenMaiDialog = dialog.findViewById(R.id.tvKhuyenMaiDialog);
        edKhuyenMaiDialog = dialog.findViewById(R.id.edKhuyenMaiDialog);
        btnGiuSanDialog = dialog.findViewById(R.id.btnGiuSan);
        btnHuy = dialog.findViewById(R.id.btnHuy);

        edGiaThueDialog.setText("   Giá Thuê: "+Cover.IntegerToVnd(item.tienSan)+"đ");
        edCaThueDialog.setText("   Tên Ca: "+item.ca);
        edGioThueDialog.setText("   Giờ Thuê: "+ Cover.caToTime(String.valueOf(item.ca)));
        edTrangThaiDialog.setText("   Trạng Thái: "+item.taiKhoan);
        if (item.taiKhoan.equals(phone)){
            edTrangThaiDialog.setText("   Trạng Thái: Giữ sân");
        }
        tvKhuyenMaiDialog.setText("Khuyến Mãi(%): ");
        edKhuyenMaiDialog.setText(""+item.soKM);
        edKhuyenMaiDialog.setEnabled(false);
        btnHuy.setOnClickListener(v -> {
            dialog.cancel();
        });
        btnGiuSanDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuThue phieuThue = new PhieuThue();
                phieuThue.nguoiThue = phone;
                phieuThue.ngayThue = ngay;
                phieuThue.caThue = String.valueOf(ca);
                phieuThue.maSan = maSan;
                phieuThue.tienSan = 0;
                phieuThue.danhGia = 0;
                phieuThue.sao = 0;
                phieuThue.soKM = item.soKM;
                if (item.taiKhoan.contains("0")){
                    Toast.makeText(getContext(),"Ca sân đã được thuê !",Toast.LENGTH_LONG).show();
                    return;
                }else if (ngay.equals(formatNgay.format(now))){
//                    if (Cover.caToPos(String.valueOf(ca)) < Cover.hourToPos(formatGio.format(now))){
//                        Toast.makeText(getContext(), "Đã vượt quá thời gian của ca thuê!!!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                    Date dateCa = Cover.ThoiGianThueToDate(ngay, String.valueOf(ca));
                    if (dateCa.compareTo(now) < 0){
                        Toast.makeText(getContext(), "Đã vượt quá thời gian của ca thuê!!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (phieuThueDAO.insert(phieuThue) > 0){
                    Toast.makeText(getContext(), "Giữ sân thành công !", Toast.LENGTH_SHORT).show();
                    setCaSan(tvNgay.getText().toString());
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();
    }
}