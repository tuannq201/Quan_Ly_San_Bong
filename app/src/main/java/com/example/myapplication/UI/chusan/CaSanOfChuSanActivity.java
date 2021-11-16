package com.example.myapplication.UI.chusan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CaSanAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CaSanOfChuSanActivity extends AppCompatActivity {
    Button btnHuyDialog,btnThueDialog,btnDate;
    TextView edGioThueDialog,edKhuyenMaiDialog,edNgayThueDialog,edCaThueDialog,edTrangThaiDialog,edGiaThueDialog;
    Dialog dialog;
    TrangThai item;
    GridView gridView;
    SimpleDateFormat formatNgay = new SimpleDateFormat("dd-MM-yyyy");
    TextView edDate;
    String ngay = "";
    int posNow = 0;
    San san;

    ArrayList<TrangThai> list1 = new ArrayList<>();
    PhieuThueDAO phieuThueDAO ;
    Date now = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_san_of_chu_san);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
        }
        san = (San) bundle.get("object_san");
        Toast.makeText(this, "Ma San: "+ san.maSan, Toast.LENGTH_SHORT).show();
        gridView = findViewById(R.id.grCaSan);
        btnDate = findViewById(R.id.btnDate);
        edDate=findViewById(R.id.tvDate);
        phieuThueDAO = new PhieuThueDAO(getApplication());

        for (int i = 1; i <= 12 ; i++) {
            list1.add(phieuThueDAO.checkTrangThai(san.maSan, String.valueOf(i), "2021-11-11"));
        }
        CaSanAdapter caSanAdapter = new CaSanAdapter(getApplication(), list1, "CS");
        gridView.setAdapter(caSanAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list1.get(i);
                openDialog();
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay();
            }
        });
        edDate.setText("       "+formatNgay.format(now));
        setCaSan(formatNgay.format(now));
    }



    private void openDialog(){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.ca_san_dialog);
        edGioThueDialog = dialog.findViewById(R.id.tvGioThueDialog);
        edGiaThueDialog = dialog.findViewById(R.id.tvGiaThueDialog);
        edCaThueDialog = dialog.findViewById(R.id.tvCaThueDialog);
        edKhuyenMaiDialog = dialog.findViewById(R.id.tvKhuyenMaiDialog);
        //edNgayThueDialog = dialog.findViewById(R.id.tvNgayThueDialog);
        edTrangThaiDialog = dialog.findViewById(R.id.tvTrangThaiDialog);
        btnHuyDialog=dialog.findViewById(R.id.btnHuyDialog);


        edGiaThueDialog.setText("Giá Thuê: "+item.tienSan);
        edCaThueDialog.setText("Tên Ca: "+item.ca);
        edGioThueDialog.setText("Giờ Thuê: "+ Cover.caToTime(String.valueOf(item.ca)));
        edTrangThaiDialog.setText("Trạng Thái: "+item.taiKhoan);
        btnHuyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
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
//                if (Cover.dateToPos(ngay, "", 1) < posNow){
//                    Toast.makeText(CaSanOfChuSanActivity.this, "Vui lòng chọn ngày khác!!!", Toast.LENGTH_SHORT).show();
//                    ngay = formatNgay.format(now);
//                }else if ((Cover.dateToPos(ngay, "", 1) > (posNow + 7))){
//                    Toast.makeText(CaSanOfChuSanActivity.this, "Chỉ được thuê sân trước 7 ngày\nvui lòng chọn ngày khác!!!", Toast.LENGTH_SHORT).show();
//                    ngay = formatNgay.format(now);
//                }
                setCaSan(ngay);
            }
        },year, month, day);
        datePickerDialog.show();
    }
    public void setCaSan(String ngay){
        list1.clear();
        //Toast.makeText(getContext(), ""+ngay, Toast.LENGTH_SHORT).show();
        for (int i = 1; i <= 12 ; i++) {
            TrangThai trangThai = phieuThueDAO.checkTrangThai(san.maSan, String.valueOf(i), ngay);
            list1.add(trangThai);
        }
        //caSanAdapter = new CaSanAdapter(getContext(), list, type);
        //gridView.setAdapter(caSanAdapter);
    }
}