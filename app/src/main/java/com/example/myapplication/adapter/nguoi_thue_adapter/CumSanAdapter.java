package com.example.myapplication.adapter.nguoi_thue_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.itf.ItemCumSanClick;

import java.util.List;

public class CumSanAdapter extends RecyclerView.Adapter<CumSanAdapter.CumSanViewHolder>{

    List<CumSan> cumSanList;
    ItemCumSanClick itemCumSanClick;
    Context context;

    SanDAO sanDAO;

    public CumSanAdapter(Context context, List<CumSan> cumSanList, ItemCumSanClick itemCumSanClick) {
        this.context = context;
        this.cumSanList = cumSanList;
        this.itemCumSanClick = itemCumSanClick;
    }

    @NonNull
    @Override
    public CumSanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cum_san, parent, false);
        return new CumSanAdapter.CumSanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CumSanViewHolder holder, int position) {
//        if (position == (cumSanList.size()-1)){
//            holder.cv.setVisibility(View.GONE);
//        }
        CumSan cumSan = cumSanList.get(position);
        sanDAO = new SanDAO(context);
        holder.tv_1.setText("Địa chỉ: "+cumSan.diaChi);
        holder.tv_2.setText(""+cumSan.tenCumSan);
        holder.tv_3.setText("Số sân: "+sanDAO.getSanByCumSan(String.valueOf(cumSan.maCumSan)).size());
        holder.tv_4.setText("Giá: "+sanDAO.giaCumSan(String.valueOf(cumSan.maCumSan)));
        holder.tv_5.setText("Loại sân: "+sanDAO.loaiSanCumSan(String.valueOf(cumSan.maCumSan)));
        holder.tv_soDG.setText("  "+cumSan.soDanhGia);
        holder.ratingBar.setRating((float) cumSan.soSao / (float) cumSan.soDanhGia);
        holder.cv.setOnClickListener(view -> {
            //Toast.makeText(context, ""+cumSan.tenCumSan, Toast.LENGTH_SHORT).show();
            itemCumSanClick.onItemClick(cumSan, "san");
        });
        holder.btn_show_dg.setOnClickListener(view -> {
            itemCumSanClick.onItemClick(cumSan, "show");
        });
    }



    @Override
    public int getItemCount() {
        return cumSanList.size();
    }

    public class CumSanViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_soDG, btn_show_dg;
        private ImageView imgSan;
        private RatingBar ratingBar;
        public CardView cv;
        public LinearLayout layout;

        public CumSanViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_diachi_cumsan);
            tv_2 = itemView.findViewById(R.id.tv_ten_cumsan);
            tv_3 = itemView.findViewById(R.id.tv_sosan_cumsan);
            tv_4 = itemView.findViewById(R.id.tv_gia_cumsan);
            imgSan = itemView.findViewById(R.id.iv_cumsan);
            cv = itemView.findViewById(R.id.cv_item_cumsan);
            tv_5 = itemView.findViewById(R.id.tv_loaisan_cumsan);
            tv_soDG = itemView.findViewById(R.id.tv_soDanhGia_cum_san);
            ratingBar = itemView.findViewById(R.id.rb_cum_san);
            btn_show_dg = itemView.findViewById(R.id.btn_show_danh_gia_cs);
        }
    }


}
