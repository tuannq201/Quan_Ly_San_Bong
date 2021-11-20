package com.example.myapplication.adapter.nguoi_thue_adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.TrangThai;

import java.util.ArrayList;
import java.util.List;

public class DemoAdapter extends ArrayAdapter<TrangThai> {

    ArrayList<TrangThai> trangThais;
    Context context;
    ImageView iv;
    PhieuThueDAO phieuThueDAO;
    public DemoAdapter(@NonNull Context context, ArrayList<TrangThai> trangThais) {
        super(context, 0, trangThais);
        this.context = context;
        this.trangThais = trangThais;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_demo_tuan, null);
        }


        final TrangThai item = trangThais.get(position);
        if (item!= null){
            iv = v.findViewById(R.id.iv_demo_tuan);

            if (item.taiKhoan.contains("0")){
                iv.setImageDrawable(context.getDrawable(R.drawable.ic_soccer_ball_black));
            }
        }
        return v;
    }
}
