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
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CaSanOfChuSanActivity extends AppCompatActivity {
    Button btnGiuSanDialog,btnDate;
    EditText edGioThueDialog,edKhuyenMaiDialog,edCaThueDialog,edTrangThaiDialog,edGiaThueDialog;
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
        if (ngay.equals(formatNgay.format(now))){
            if (Cover.caToPos(String.valueOf(ca)) < Cover.hourToPos(formatGio.format(now))){
                Toast.makeText(getApplication(), "đã quá thời gian thuê!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (item.taiKhoan.contains("0")){
            Toast.makeText(getApplication(), "Ca "+ca+" đã được thuê, vui lòng chọn ca khác ", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.ca_san_dialog);
        edGioThueDialog = dialog.findViewById(R.id.tvGioThueDialog);
        edGiaThueDialog = dialog.findViewById(R.id.tvGiaThueDialog);
        edCaThueDialog = dialog.findViewById(R.id.tvCaThueDialog);
        edKhuyenMaiDialog = dialog.findViewById(R.id.tvKhuyenMaiDialog);
        //edNgayThueDialog = dialog.findViewById(R.id.tvNgayThueDialog);
        edTrangThaiDialog = dialog.findViewById(R.id.tvTrangThaiDialog);
        btnGiuSanDialog = dialog.findViewById(R.id.btnGiuSan);


        edGiaThueDialog.setText("   Giá Thuê: "+item.tienSan);
        edCaThueDialog.setText("   Tên Ca: "+item.ca);
        edGioThueDialog.setText("   Giờ Thuê: "+ Cover.caToTime(String.valueOf(item.ca)));
        edTrangThaiDialog.setText("   Trạng Thái: "+item.taiKhoan);
        edKhuyenMaiDialog.setText(""+Cover.KhuyenMai1(item.ca));

        btnGiuSanDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuThue phieuThue = new PhieuThue();
                phieuThue.nguoiThue = phone;
                phieuThue.ngayThue = ngay;
                phieuThue.caThue = String.valueOf(ca);
                phieuThue.maSan = san.maSan;
                phieuThue.tienSan = item.tienSan;
                phieuThue.danhGia = 0;
                phieuThue.sao = 0;
                if (phieuThueDAO.insert(phieuThue) > 0){
                    Toast.makeText(getApplication(), "Giữ sân thành công", Toast.LENGTH_SHORT).show();
                    setCaSan(ngay);
                    dialog.dismiss();
                }else {
                    Toast.makeText(getApplication(), "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();
    }
    public void updateKM(){
        int phanTram = Integer.parseInt(edKhuyenMaiDialog.getText().toString());
        int tienSan = item.tienSan;
        item.tienSan = Cover.KhuyenMaiTheoPhanTram(phanTram,tienSan);
        PhieuThue phieuThue = new PhieuThue();
        phieuThue.maPT = item.maPT;
            Log.e("////", "updateKM: "+item.maPT );
        phieuThue.nguoiThue = item.taiKhoan;
        phieuThue.ngayThue = ngay;
        phieuThue.caThue = item.ca;
        phieuThue.maSan = item.maSan;
        phieuThue.tienSan = item.tienSan;
        phieuThueDAO.update(phieuThue);
        Log.e("////", "updateKM: "+phieuThueDAO.update(phieuThue));
        edKhuyenMaiDialog.setText("   Khuyến Mãi:"+phanTram+"%");
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
        //Toast.makeText(getContext(), ""+ngay, Toast.LENGTH_SHORT).show();
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