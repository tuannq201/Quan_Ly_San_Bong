package com.example.myapplication.UI.chusan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CaSanAdapter;
import com.example.myapplication.dao.KhuyenMaiDAO;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.KhuyenMai;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CaSanOfChuSanActivity extends AppCompatActivity {
    Button btnGiuSanDialog,btnDate, btnHuy;
    TextView tvGioThueDialog,tvCaThueDialog,tvTrangThaiDialog,tvGiaThueDialog,tvKhuyenMaiDialog;
    EditText edKhuyenMaiDialog;
    Dialog dialog;
    TrangThai item;
    GridView gridView;
    SimpleDateFormat formatNgay = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatGio = new SimpleDateFormat("HH:mm");
    TextView edDate;
    String ngay = "";
    String phone;
    String type = "";
    San san;
    KhuyenMaiDAO khuyenMaiDAO;
//    public CaSanOfChuSanActivity(String type){
//        this.type=type;
//    }
    ArrayList<TrangThai> list1 = new ArrayList<>();
    PhieuThueDAO phieuThueDAO ;
    Date now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_san_of_chu_san);
        // ÁNh xạ
        gridView = findViewById(R.id.grCaSan);
        btnDate = findViewById(R.id.btnDate);
        edDate=findViewById(R.id.tvDate);

        SharedPreferences pref = getApplication().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        phone = pref.getString("PHONE","");

        Bundle bundle = getIntent().getExtras();
        san = (San) bundle.get("object_san");
        Toast.makeText(this, "Ma San: "+ san.maSan, Toast.LENGTH_SHORT).show();


        now = new Date();
        ngay = formatNgay.format(now);

        phieuThueDAO = new PhieuThueDAO(getApplication());
        khuyenMaiDAO = new KhuyenMaiDAO(getApplication());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (type.equals("NT")){

                }else {
                    item = list1.get(i);
                    openDialog((i + 1));
                }
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay();
            }
        });
        list1 = new ArrayList<>();
        edDate.setText("       "+formatNgay.format(now));
        setCaSan(formatNgay.format(now));
    }
    
    private void openDialog(int ca){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.ca_san_dialog);
        tvGioThueDialog = dialog.findViewById(R.id.tvGioThueDialog);
        tvGiaThueDialog = dialog.findViewById(R.id.tvGiaThueDialog);
        tvCaThueDialog = dialog.findViewById(R.id.tvCaThueDialog);
        tvKhuyenMaiDialog = dialog.findViewById(R.id.tvKhuyenMaiDialog);
        //edNgayThueDialog = dialog.findViewById(R.id.tvNgayThueDialog);
        tvTrangThaiDialog = dialog.findViewById(R.id.tvTrangThaiDialog);
        edKhuyenMaiDialog = dialog.findViewById(R.id.edKhuyenMaiDialog);
        btnGiuSanDialog = dialog.findViewById(R.id.btnGiuSan);
        btnHuy = dialog.findViewById(R.id.btnHuy);


        tvGiaThueDialog.setText("Giá Thuê: "+item.tienSan);
        tvCaThueDialog.setText("Tên Ca: "+item.ca);
        tvGioThueDialog.setText("Giờ Thuê: "+ Cover.caToTime(String.valueOf(item.ca)));
        tvTrangThaiDialog.setText("Trạng Thái: "+item.taiKhoan);
        tvKhuyenMaiDialog.setText("Khuyến Mãi(%): ");
        edKhuyenMaiDialog.setText(""+ item.soKM);

        btnHuy.setOnClickListener(v -> {
            dialog.cancel();
        });
        btnGiuSanDialog.setText("Cập Nhập");
        btnGiuSanDialog.setOnClickListener(v -> {
            if (item.taiKhoan.contains("0")){
                Toast.makeText(getApplication(), "Ca sân đã được thuê không thể sửa", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                return;
            }else {
                int maKM = Integer.parseInt(edKhuyenMaiDialog.getText().toString());
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.soKM = maKM;
                khuyenMai.maSan = san.maSan;
                khuyenMai.ca = item.ca;
                khuyenMai.ngay = ngay;
                if (khuyenMaiDAO.insert(khuyenMai) >0){
                    Toast.makeText(getApplication(), "Cập Nhập Thành Công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplication(), "Cập Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
            setCaSan(ngay);
        });
        dialog.show();
    }
    public void chonNgay(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                edDate.setText("       "+Cover.formater(y, m, d));
                ngay = Cover.formater(y, m, d);

                setCaSan(ngay);
            }
        },year, month, day);
        datePickerDialog.show();
    }
    public void setCaSan(String ngay){
        list1.clear();
        for (int i = 1; i <= 12 ; i++) {
            TrangThai trangThai = phieuThueDAO.checkTrangThai(san.maSan, String.valueOf(i), ngay);
            list1.add(trangThai);
        }
        CaSanAdapter caSanAdapter = new CaSanAdapter(getApplication(), list1, "CS");
        gridView.setAdapter(caSanAdapter);
    }
    private void capNhatPT(){

    }
}