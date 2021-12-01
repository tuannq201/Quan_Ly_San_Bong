package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.itf.ITFOnItenClick;

import java.util.List;

public class RecyclerCumSanAdapter extends RecyclerView.Adapter<RecyclerCumSanAdapter.CumSanViewHolder> {
    private Context context;
    private List<CumSan> cumSanList;
    private ITFOnItenClick itfOnItenClick;
    public RecyclerCumSanAdapter(Context context, List<CumSan> cumSanList, ITFOnItenClick itfOnItenClick) {
        this.context = context;
        this.cumSanList = cumSanList;
        this.itfOnItenClick = itfOnItenClick;
    }

    @NonNull
    @Override
    public CumSanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recy_cum_sanlayout, parent, false);
        return new CumSanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CumSanViewHolder holder, int position) {
        CumSan cumSan = cumSanList.get(position);
        if (cumSan == null){
            return;
        }
        holder.tvTenCumSan.setText("Tên Cụm Sân: "+ cumSan.tenCumSan);
        holder.tvDiaChi.setText("Địa Chỉ: "+ cumSan.diaChi);
        holder.imgDelete.setOnClickListener(v -> {
            itfOnItenClick.onItemClick(cumSan, 1);
        });
        holder.layout.setOnClickListener(v -> {
            itfOnItenClick.onItemClick(cumSan, 0);
        });
    }

    @Override
    public int getItemCount() {
        return cumSanList.size();
    }

    public void setCumSanList(List<CumSan> cumSanList) {
        this.cumSanList = cumSanList;
        notifyDataSetChanged();
    }

    public class CumSanViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTenCumSan, tvDiaChi;
        private ImageView imgDelete;
        private LinearLayout layout;
        public CumSanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenCumSan = itemView.findViewById(R.id.text_tenCum);
            tvDiaChi = itemView.findViewById(R.id.text_diaChi);
            imgDelete = itemView.findViewById(R.id.img_delete);
            layout = itemView.findViewById(R.id.layout_cum_san);
        }
    }
}
