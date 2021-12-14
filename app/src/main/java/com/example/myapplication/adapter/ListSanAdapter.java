package com.example.myapplication.adapter;

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

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.myapplication.R;
import com.example.myapplication.UI.chusan.ListSanFragment;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.util.Cover;

import java.util.List;

public class ListSanAdapter extends RecyclerView.Adapter<ListSanAdapter.ListsanViewHolder>{
    private List<San> listSan;
    private Context context;
//    private ListSanFragment fragment;
    private ITFOnItenClick itfOnItenClick;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();


    public ListSanAdapter(Context context, List<San> listSan, ITFOnItenClick itfOnItenClick) {
        this.context = context;
        this.listSan = listSan;
        this.itfOnItenClick = itfOnItenClick;
    }

    @NonNull
    @Override
    public ListsanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_san_cs_layout, parent, false);
        return new ListsanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListsanViewHolder holder, int position) {
        San san = listSan.get(position);
        if (san == null){
            return;
        }
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(san.maSan));
        holder.layoutDelete.setOnClickListener(v -> {
            itfOnItenClick.onItemClick(san, 2);
        });
        holder.tvTenSan.setText("Tên Sân: "+ san.tenSan);
        holder.tvLoaiSan.setText("Loại Sân: "+ san.loaiSan);
        holder.tvGiaSan.setText("Giá sân: "+ Cover.IntegerToVnd(san.giaSan)+"vnđ");
        holder.tvSoDanhGia.setText("  "+san.soDanhGia);
        holder.ratingBar.setRating((float) san.soSao / (float) san.soDanhGia);
        try {
            holder.imgSan.setImageBitmap(Cover.ByteToBitmap(san.anhSan));
        }catch (Exception e){

        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itfOnItenClick.onItemClick(san, 0);
            }
        });
        holder.layout.setOnLongClickListener(v -> {
            itfOnItenClick.onItemClick(san, 1);
            return true;
        });

        holder.tv_show_dg.setOnClickListener(view -> {
            itfOnItenClick.onItemClick(san, 6);
        });


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

    public class ListsanViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTenSan, tvLoaiSan, tvGiaSan, tvSoDanhGia, tv_show_dg;
        private ImageView imgSan;
        private LinearLayout layoutDelete, layout;
        private SwipeRevealLayout swipeRevealLayout;
        private RatingBar ratingBar;
//        private LinearLayout layout;
        public int position;

        public ListsanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSan = itemView.findViewById(R.id.text_ten_san);
            tvLoaiSan = itemView.findViewById(R.id.text_loai_san);
            tvGiaSan = itemView.findViewById(R.id.text_gia_san);
            imgSan = itemView.findViewById(R.id.img_san);
            layoutDelete = itemView.findViewById(R.id.layout_delete);
            swipeRevealLayout = itemView.findViewById(R.id.swip_layout_item_san);
            layout = itemView.findViewById(R.id.layout_cs);
            ratingBar = itemView.findViewById(R.id.rb_san);
            tvSoDanhGia = itemView.findViewById(R.id.tv_soDanhGiaSan);
            tv_show_dg = itemView.findViewById(R.id.tv_show_dg_san);
//            cv = (CardView) itemView;
        }
    }


}
