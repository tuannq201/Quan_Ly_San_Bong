package com.example.myapplication.UI.chusan;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
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
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.Cover;

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
    TrangThai trangThai;
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
                Log.e("//", "onItemSelected: "+maCumSanHienTai);
                updateSpinnerSan(String.valueOf(maCumSanHienTai));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
            TrangThai trangThai = phieuThueDAO.checkTrangThai(maSanHienTai, String.valueOf(i), ngay);
            list.add(trangThai);
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
            TrangThai trangThai = list.get(i);
            int tien = trangThai.tienSan;
            String tt = trangThai.taiKhoan;
            if (tt.contains("0")){
                tongNgay = tongNgay + tien;
            }
        }
        tvTongNgay.setText("Tổng thu nhập ngày: "+Cover.IntegerToVnd(tongNgay)+" VNĐ");
    }
}