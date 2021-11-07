package com.example.myapplication.adapter.nguoi_thue_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.entity.PhieuThue;

import java.util.List;

public class SDTAdapter extends ArrayAdapter<PhieuThue> {
    Context context;
    List<PhieuThue> list;
    TextView tv_1, tv_2, tv_3, tv_4;
    ImageView iv;
    public SDTAdapter(@NonNull Context context, List<PhieuThue> list) {
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
            v = inflater.inflate(R.layout.item_san_da_thue, null);
        }
        final PhieuThue item = list.get(position);
        if (item!= null){
            tv_1 = v.findViewById(R.id.tv_dia_chi_san_ddk);
            tv_2 = v.findViewById(R.id.tv_thoi_gian_ddk);
            tv_3 = v.findViewById(R.id.tv_loai_san_ddk);
            tv_4 = v.findViewById(R.id.tv_gia_san_ddk);
            iv = v.findViewById(R.id.iv_san_ddk);

            tv_1.setText("hhhhhhhh");
            tv_2.setText("hhhhhhhh");
            tv_3.setText("hhhhhhhh");
            tv_4.setText("hhhhhhhh");

        }return v;
    }
}
