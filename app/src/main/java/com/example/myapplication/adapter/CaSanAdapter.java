package com.example.myapplication.adapter;

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
import com.example.myapplication.UI.chusan.CaSanFragment;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;

import java.util.List;

public class CaSanAdapter extends ArrayAdapter<PhieuThue> {
    private Context context;
    private List<PhieuThue> list;
    private CaSanFragment fragment;
    TextView tvTenCa,tvTrangThai,tvKhuyenMai;
    ImageView imgCaSan;
    public CaSanAdapter(@NonNull Context context, CaSanFragment fragment, List<PhieuThue> list) {
        super(context,0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_ca_san,null);
        }
        final PhieuThue item = list.get(position);
        if (item != null){
            SanDAO sanDAO = new SanDAO(context);
            San san = sanDAO.getID(String.valueOf(item.maSan));
            tvTenCa = view.findViewById(R.id.tvTenCa);
            tvTenCa.setText("Tên Ca: "+san.tenSan);
            tvTrangThai = view.findViewById(R.id.tvTrangThai);
            tvTrangThai.setText("Trạng Thái: ");
            tvKhuyenMai = view.findViewById(R.id.tvKhuyenMai);

        }
        return view;
    }
}
