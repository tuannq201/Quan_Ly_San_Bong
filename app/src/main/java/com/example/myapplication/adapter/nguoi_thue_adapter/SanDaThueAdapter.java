package com.example.myapplication.adapter.nguoi_thue_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.util.Cover;

import java.util.List;

public class SanDaThueAdapter extends RecyclerView.Adapter<SanDaThueAdapter.ViewHolder> {

    List<PhieuThue> phieuThueList;
    Context context;
    ITFOnItenClick itfOnItenClick;
    SanDAO sanDAO;


    public SanDaThueAdapter(Context context, List<PhieuThue> phieuThueList, ITFOnItenClick itfOnItenClick) {
        this.context = context;
        this.phieuThueList = phieuThueList;
        this.itfOnItenClick = itfOnItenClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_san_da_thue, parent, false);
        return new SanDaThueAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuThue phieuThue = phieuThueList.get(position);
        sanDAO = new SanDAO(context);
        String diaChi = "";
        String loaiSan = "";
        byte[] hinh;
        try {
            San san = sanDAO.getID(String.valueOf(phieuThue.maSan));
//            diaChi = san.diaChi;
            loaiSan = san.loaiSan;
            hinh = san.anhSan;
            holder.iv.setImageBitmap(Cover.ByteToBitmap(hinh));
//            Glide.with(context).load(R.drawable.br_sanbong)
//                    .apply(bitmapTransform(new BlurTransformation(22)))
//                    .into((ImageView) view.findViewById(R.id.imBg));



        }catch (Exception e){

        }
        holder.tv_1.setText("Sân: "+diaChi);
        holder.tv_2.setText("Thời Gian: "+caToTime(String.valueOf(phieuThue.caThue))+" ngày:"+phieuThue.ngayThue);
        holder.tv_3.setText("Loại sân: "+loaiSan);
        holder.tv_4.setText("Giá sân: "+phieuThue.tienSan);
    }

    @Override
    public int getItemCount() {
        return phieuThueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_1, tv_2, tv_3, tv_4, tv_5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_ten_cum_san_ddk);
            tv_2 = itemView.findViewById(R.id.tv_thoi_gian_ddk);
            tv_3 = itemView.findViewById(R.id.tv_loai_san_ddk);
            tv_4 = itemView.findViewById(R.id.tv_gia_san_ddk);
            iv = itemView.findViewById(R.id.iv_san_ddk);
        }
    }

    public String caToTime(String ca){
        if (ca.contains("1")){
            return "6:00-7:00";
        }
        if (ca.contains("2")){
            return "7:30-8:30";
        }
        if (ca.contains("3")){
            return "9:00-10:00";
        }
        if (ca.contains("4")){
            return "10:30-11:30";
        }
        if (ca.contains("5")){
            return "12:00-13:00";
        }
        if (ca.contains("6")){
            return "13:30-14:30";
        }
        if (ca.contains("7")){
            return "15:00-16:00";
        }
        if (ca.contains("8")){
            return "16:30-17:30";
        }
        if (ca.contains("9")){
            return "18:00-19:00";
        }
        if (ca.contains("10")){
            return "19:30-20:30";
        }
        if (ca.contains("11")){
            return "21:00-22:00";
        }
        if (ca.contains("12")){
            return "22:30-23:30";
        }
        return ca;
    }
}
