package com.example.myapplication.UI.chusan;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CaSanAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.util.ArrayList;

public class CaSanOfChuSanActivity extends AppCompatActivity {
    Button btnHuyDialog,btnThueDialog;
    TextView edGioThueDialog,edKhuyenMaiDialog,edNgayThueDialog,edCaThueDialog,edTrangThaiDialog,edGiaThueDialog;
    Dialog dialog;
    TrangThai item;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_san_of_chu_san);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
        }
        San san = (San) bundle.get("object_san");
        gridView = findViewById(R.id.grCaSan);
        PhieuThueDAO phieuThueDAO = new PhieuThueDAO(getApplication());
        ArrayList<TrangThai> list1 = new ArrayList<>();
        for (int i = 1; i <= 12 ; i++) {
            list1.add(phieuThueDAO.checkTrangThai(1, String.valueOf(i), "2021-11-11"));
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
}