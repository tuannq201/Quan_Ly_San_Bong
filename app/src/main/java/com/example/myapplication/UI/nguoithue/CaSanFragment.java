package com.example.myapplication.UI.nguoithue;

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
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CaSanAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.text.ParseException;
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
    SimpleDateFormat formatNgay = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatGio = new SimpleDateFormat("HH:mm");
    TextView tv_cumSan_tenSan, tv_diachi, tv_giaSan, tv_gia_thue, tv_thoiGian, btn_huy_thue, btn_thue, tv_km;
    String ngay = "";
    TrangThai trangThai;
    String phone;
    //int posNow = 0;
    Date now;

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

        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        phone = pref.getString("PHONE","");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca_san, container, false);
        grv = v.findViewById(R.id.grv_ca_san);
        tv_san_ngay = v.findViewById(R.id.tv_san_ngay);
        btn_chon_ngay = v.findViewById(R.id.btn_chonNgay);

        now = new Date();
        ngay = formatNgay.format(now);
        //posNow = Cover.dateToPos(formatNgay.format(now), "", 1);
        tv_san_ngay.setText(""+san.tenSan+" ,"+ngay+" ,"+ formatGio.format(now));
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
        setCaSan(formatNgay.format(now));

        return v;
    }

    public void openDialog(int ca){
        if (ngay.equals(formatNgay.format(now))){
            Date dateCa = Cover.ThoiGianThueToDate(ngay, String.valueOf(ca));
            if (dateCa.compareTo(now) < 0){
                Toast.makeText(getContext(), "đã quá thời gian thuê!!!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (trangThai.taiKhoan.contains("0")){
            Toast.makeText(getContext(), "Ca "+ca+" đã được thuê, vui lòng chọn ca khác ", Toast.LENGTH_SHORT).show();
            return;
        }
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
        int km = trangThai.soKM;
        int thanhToan = san.giaSan - (san.giaSan * km / 100);
        tv_km.setText("Khuyến mãi: "+km+"%");
        tv_gia_thue.setText("Giá thuê: "+Cover.IntegerToVnd(thanhToan)+"vnđ");

        btn_huy_thue.setOnClickListener(view -> {
            dialog.dismiss();
        });
        btn_thue.setOnClickListener(view -> {
            PhieuThue phieuThue = new PhieuThue();
            phieuThue.nguoiThue = phone;
            phieuThue.ngayThue = ngay;
            phieuThue.caThue = String.valueOf(ca);
            phieuThue.maSan = san.maSan;
            phieuThue.tienSan = thanhToan;
            phieuThue.soKM = km;
            phieuThue.danhGia = 0;
            phieuThue.sao = 0;
            if (phieuThueDAO.insert(phieuThue) > 0){
                Toast.makeText(getContext(), "Thuê thành công", Toast.LENGTH_SHORT).show();
                setCaSan(ngay);
                dialog.dismiss();
            }else {
                Toast.makeText(getContext(), "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
            }

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
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.add(Calendar.DAY_OF_YEAR, +7);
                Date dateAdd7 = calendar.getTime();
                Date dateChon = new Date();
                String strNow = formatNgay.format(now)+ " 00-00";
                try {
                    dateChon = formatNgay.parse(ngay);
                    Date dateNow = Cover.format.parse(strNow);
                    if (dateChon.compareTo(dateNow) < 0){
                        Toast.makeText(getContext(), "Vui lòng chọn ngày khác!!!", Toast.LENGTH_SHORT).show();
                        ngay = formatNgay.format(now);
                        tv_san_ngay.setText(ngay);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (dateChon.compareTo(dateAdd7) > 0){
                    Toast.makeText(getContext(), "Chỉ được thuê sân trước 7 ngày\nvui lòng chọn ngày khác!!!", Toast.LENGTH_SHORT).show();
                    ngay = formatNgay.format(now);
                    tv_san_ngay.setText(ngay);
                }

                setCaSan(ngay);
            }
        },year, month, day);
        datePickerDialog.show();
    }

    public void setCaSan(String ngay){
        list.clear();
        //Toast.makeText(getContext(), ""+ngay, Toast.LENGTH_SHORT).show();
        for (int i = 1; i <= 12 ; i++) {
            TrangThai trangThai = phieuThueDAO.checkTrangThai(san.maSan, String.valueOf(i), ngay);
            list.add(trangThai);
        }
        caSanAdapter = new CaSanAdapter(getContext(), list, type);
        grv.setAdapter(caSanAdapter);
    }


}