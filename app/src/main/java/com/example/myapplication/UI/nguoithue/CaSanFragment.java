package com.example.myapplication.UI.nguoithue;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CaSanAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaSanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaSanFragment extends Fragment {

    San san;
    String type = "";
    GridView grv;
    TextView tv_san_ngay, btn_chon_ngay;
    CaSanAdapter caSanAdapter;
    PhieuThueDAO phieuThueDAO;
    CumSanDAO cumSanDAO;
    SanDAO sanDAO;
    ArrayList<TrangThai> list;
    Dialog dialog;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat format2 = new SimpleDateFormat("hh:mm");
    TextView tv_cumSan_tenSan, tv_diachi, tv_giaSan, tv_gia_thue, tv_thoiGian, btn_huy_thue, btn_thue, tv_km;
    String ngay = "";
    TrangThai trangThai;

    public CaSanFragment(San san, String type) {//type: CS or NT
        this.san = san;
        this.type = type;
    }


    public static CaSanFragment newInstance(San san, String type) {
        CaSanFragment fragment = new CaSanFragment(san, type);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phieuThueDAO = new PhieuThueDAO(getContext());
        sanDAO = new SanDAO(getContext());
        cumSanDAO = new CumSanDAO(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca_san, container, false);
        grv = v.findViewById(R.id.grv_ca_san);
        tv_san_ngay = v.findViewById(R.id.tv_san_ngay);
        btn_chon_ngay = v.findViewById(R.id.btn_chonNgay);

        Date now = new Date();
        ngay = format.format(now);
        tv_san_ngay.setText(""+san.tenSan+" ,"+ngay+" ,"+format2.format(now));
        btn_chon_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay();
            }
        });
        grv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (type.equals("CS")){

                }else {
                    //người thuê
                    trangThai = list.get(pos);
                    openDialog((pos+1));
                }
            }
        });

        list = new ArrayList<>();
        setCaSan(format.format(now));

        return v;
    }

    public void openDialog(int ca){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_thue_san);
        tv_cumSan_tenSan = dialog.findViewById(R.id.tv_cumSan_thue);
        tv_diachi = dialog.findViewById(R.id.tv_diaChi_thue);
        tv_thoiGian = dialog.findViewById(R.id.tv_ca_thue);
        tv_giaSan = dialog.findViewById(R.id.tv_giaSan_thue);
        tv_gia_thue = dialog.findViewById(R.id.tv_gia_thue);
        tv_km = dialog.findViewById(R.id.tv_km_thue);

        btn_huy_thue = dialog.findViewById(R.id.btn_huy_thue);
        btn_thue = dialog.findViewById(R.id.btn_thue);

        tv_cumSan_tenSan.setText("Sân: "+cumSanDAO.getCumSanBySan(String.valueOf(san.maSan)).tenCumSan+" - "+san.tenSan);
        tv_diachi.setText("Địa chỉ: "+cumSanDAO.getCumSanBySan(String.valueOf(san.maSan)).diaChi);
        tv_thoiGian.setText(""+Cover.caToTime(String.valueOf(ca))+" ngày: "+ngay);
        tv_giaSan.setText("Giá sân: "+Cover.IntegerToVnd(san.giaSan)+"vnđ");
        int km = Cover.KhuyenMai1(String.valueOf(ca));
        int thanhToan = san.giaSan - san.giaSan * Cover.KhuyenMai1(String.valueOf(ca)) / 100;
        tv_km.setText("Khuyến mãi: "+km+"%");
        tv_gia_thue.setText("Giá thuê: "+Cover.IntegerToVnd(thanhToan)+"vnđ");
        btn_huy_thue.setOnClickListener(view -> {
            dialog.dismiss();
        });


        dialog.show();
    }

    public void chonNgay(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                tv_san_ngay.setText(""+san.tenSan+" ,"+Cover.formater(y, m, d));
                ngay = Cover.formater(y, m, d);
                setCaSan(ngay);
            }
        },year, month, day);
        datePickerDialog.show();
    }

    public void setCaSan(String ngay){
        list.clear();
        //Toast.makeText(getContext(), ""+ngay, Toast.LENGTH_SHORT).show();
        for (int i = 1; i <= 12 ; i++) {
            list.add(phieuThueDAO.checkTrangThai(san.maSan, String.valueOf(i), ngay));
        }
        caSanAdapter = new CaSanAdapter(getContext(), list, type);
        grv.setAdapter(caSanAdapter);
    }
}