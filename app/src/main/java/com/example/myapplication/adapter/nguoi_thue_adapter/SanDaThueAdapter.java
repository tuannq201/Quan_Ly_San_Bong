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
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.util.ImageCover;

import java.util.ArrayList;

public class SanDaThueAdapter extends ArrayAdapter<PhieuThue> {
    ArrayList<PhieuThue> phieuThueArrayList;
    SanDAO sanDAO;
    Context context;
    TextView tv_1, tv_2, tv_3, tv_4;
    ImageView iv;
    public SanDaThueAdapter(@NonNull Context context, ArrayList<PhieuThue> phieuThueArrayList) {
        super(context, 0, phieuThueArrayList);
        this.phieuThueArrayList = phieuThueArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_san_da_thue,null);
        }
        sanDAO = new SanDAO(context);
        PhieuThue item = phieuThueArrayList.get(position);
        if (item != null){
            tv_1 = v.findViewById(R.id.tv_dia_chi_san_ddk);
            tv_2 = v.findViewById(R.id.tv_thoi_gian_ddk);
            tv_3 = v.findViewById(R.id.tv_loai_san_ddk);
            tv_4 = v.findViewById(R.id.tv_gia_san_ddk);
            iv = v.findViewById(R.id.iv_san_ddk);

            San san = sanDAO.getID(String.valueOf(item.maSan));



            try {
                tv_2.setText(""+item.caThue+" "+item.ngayThue);
                tv_4.setText("Giá sân: "+item.tienSan);
                tv_3.setText("Loại sân: "+san.loaiSan);
                tv_1.setText("Sân: "+san.diaChi);
                iv.setImageBitmap(ImageCover.ByteToBitmap(san.anhSan));
            }catch (Exception e){

            }
        }
        return v;

    }
}
