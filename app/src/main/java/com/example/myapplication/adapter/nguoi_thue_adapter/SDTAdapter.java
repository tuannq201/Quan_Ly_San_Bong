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
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.util.Cover;

import java.util.List;

public class SDTAdapter extends ArrayAdapter<PhieuThue> {
    Context context;
    List<PhieuThue> list;
    CumSanDAO cumSanDAO;
    SanDAO sanDAO;
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
        final PhieuThue phieuThue = list.get(position);
        sanDAO = new SanDAO(context);
        cumSanDAO = new CumSanDAO(context);
        if (phieuThue!= null){
            tv_1 = v.findViewById(R.id.tv_ten_cum_san_ddk);
            tv_2 = v.findViewById(R.id.tv_thoi_gian_ddk);
            tv_3 = v.findViewById(R.id.tv_loai_san_ddk);
            tv_4 = v.findViewById(R.id.tv_gia_san_ddk);
            iv = v.findViewById(R.id.iv_san_ddk);

            try {
                tv_1.setText("Sân "+cumSanDAO.getCumSanBySan(String.valueOf(phieuThue.maSan)).tenCumSan);
                tv_2.setText("Địa chỉ: "+cumSanDAO.getCumSanBySan(String.valueOf(phieuThue.maSan)).diaChi);
                tv_3.setText("Loại sân: sân "+sanDAO.getID(String.valueOf(phieuThue.maSan)).loaiSan);
                tv_4.setText("Giá: "+ Cover.IntegerToVnd(phieuThue.tienSan)+"vnđ");
            }catch (Exception e){
                tv_1.setText("hhhhhhhh");
                tv_2.setText("hhhhhhhh");
                tv_3.setText("hhhhhhhh");
                tv_4.setText("hhhhhhhh");
            }


        }return v;
    }
}
