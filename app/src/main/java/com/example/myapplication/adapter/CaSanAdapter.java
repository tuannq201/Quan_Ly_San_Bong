package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.util.ArrayList;

public class CaSanAdapter extends ArrayAdapter<TrangThai> {

    private Context context;
    private ArrayList<TrangThai> list;
    String type;
    TextView tvTenCa,tvTrangThai,tvKhuyenMai;

    public CaSanAdapter(Context context, ArrayList<TrangThai> list, String type) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.type = type;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_ca_san,null);
        }
        final TrangThai item = list.get(position);
        if (item != null){
            tvTenCa = view.findViewById(R.id.tvTenCa);
            //Log.e("//===","==========="+ Cover.caToTime(item.ca));
            tvTenCa.setText("Ca: "+ Cover.caToTime(item.ca));
            tvTrangThai = view.findViewById(R.id.tvTrangThai);
            tvTrangThai.setText("Trạng Thái: "+item.taiKhoan);
            tvKhuyenMai = view.findViewById(R.id.tvKhuyenMai);
            tvKhuyenMai.setText("Khuyến Mãi: "+Cover.KhuyenMai1(item.ca)+"%");
        }
        return view;
    }

}
