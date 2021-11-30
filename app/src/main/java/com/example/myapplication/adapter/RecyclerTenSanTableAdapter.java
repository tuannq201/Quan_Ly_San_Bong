package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.myapplication.R;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.util.Cover;

import java.util.List;

public class RecyclerTenSanTableAdapter extends RecyclerView.Adapter<RecyclerTenSanTableAdapter.SanViewHolder>{
    private List<San> listSan;
    private Context context;
//    private ListSanFragment fragment;
    private ITFOnItenClick itfOnItenClick;


    public RecyclerTenSanTableAdapter(Context context, List<San> listSan, ITFOnItenClick itfOnItenClick) {
        this.context = context;
        this.listSan = listSan;
        this.itfOnItenClick = itfOnItenClick;
    }

    @NonNull
    @Override
    public SanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_recycler_layout, parent, false);
        return new SanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanViewHolder holder, int position) {
        San san = listSan.get(position);
        if (san == null){
            return;
        }
        holder.tvTenSan.setText(""+ san.tenSan);
    }

    @Override
    public int getItemCount() {
        return listSan.size();
    }
    public San getItem(int position){
        if (listSan == null || position > listSan.size()){
            return null;
        }
        return listSan.get(position);
    }
    public void setList(List<San> listSan) {
        this.listSan = listSan;
        notifyDataSetChanged();
    }

    public class SanViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTenSan, tvLoaiSan, tvGiaSan, tvSoDanhGia;
        public int position;

        public SanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSan = itemView.findViewById(R.id.tv_san_number);
        }
    }


}
