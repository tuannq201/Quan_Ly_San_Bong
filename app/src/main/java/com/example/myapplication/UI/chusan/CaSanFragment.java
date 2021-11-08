package com.example.myapplication.UI.chusan;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CaSanAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.util.ArrayList;
import java.util.List;


public class CaSanFragment extends Fragment {
    PhieuThueDAO dao;

    List<PhieuThue> list;
    PhieuThueDAO adapter;
    Button btnHuyDialog,btnThueDialog;
    EditText edGioThueDialog,edKhuyenMaiDialog,edNgayThueDialog,edCaThueDialog,edTrangThaiDialog,edGiaThueDialog;
    Dialog dialog;
    TrangThai item;
    Context context;

    GridView gridView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_san, container, false);
        gridView = view.findViewById(R.id.grCaSan);
        PhieuThueDAO phieuThueDAO = new PhieuThueDAO(getContext());
        ArrayList<TrangThai> list1 = new ArrayList<>();
        for (int i = 1; i <= 12 ; i++) {
            list1.add(phieuThueDAO.checkTrangThai(1, String.valueOf(i), "2021-11-11"));
        }
        CaSanAdapter caSanAdapter = new CaSanAdapter(getContext(), list1);
        gridView.setAdapter(caSanAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list1.get(i);
                openDialog();
            }
        });
        return view;
    }

    private void openDialog(){

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.ca_san_dialog);
        edGioThueDialog = dialog.findViewById(R.id.edGioThueDialog);
        edGiaThueDialog = dialog.findViewById(R.id.edGiaThueDialog);
        edCaThueDialog = dialog.findViewById(R.id.edCaThueDialog);
        edKhuyenMaiDialog = dialog.findViewById(R.id.edKhuyenMaiDialog);
        //edNgayThueDialog = dialog.findViewById(R.id.edNgayThueDialog);
        edTrangThaiDialog = dialog.findViewById(R.id.edTrangThaiDialog);
        btnHuyDialog=dialog.findViewById(R.id.btHuyDialog);


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