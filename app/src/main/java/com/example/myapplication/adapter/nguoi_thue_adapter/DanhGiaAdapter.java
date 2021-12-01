package com.example.myapplication.adapter.nguoi_thue_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.entity.DanhGia;
import com.example.myapplication.util.Cover;

import java.util.List;

public class DanhGiaAdapter extends ArrayAdapter {
    List<DanhGia> list;
    Context context;
    TextView tv_danhGia, tv_tenNT, tv_tenSan, tv_ngay;
    RatingBar rb;


    public DanhGiaAdapter(@NonNull Context context, List<DanhGia> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_show_danh_gia, parent, false);
        }

        final DanhGia danhGia = list.get(position);
        if (danhGia != null){
            tv_danhGia = v.findViewById(R.id.tv_phanHoi_isdg);
            tv_ngay = v.findViewById(R.id.tv_ngay_isdg);
            tv_tenNT = v.findViewById(R.id.tv_tenNT_isdg);
            tv_tenSan = v.findViewById(R.id.tv_tenSan_isdg);
            rb = v.findViewById(R.id.rb_isdg);

            tv_tenNT.setText(danhGia.tenNT);
            tv_tenSan.setText(danhGia.tenSan+"   Giá thuê: "+ Cover.IntegerToVnd(danhGia.giaThue)+"đ");
            rb.setRating(danhGia.sao);
            tv_danhGia.setText(danhGia.danhGia);
            tv_ngay.setText(danhGia.ngayThue);
        }
        return v;
    }
}
