package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.UI.chusan.ListSanFragment;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.util.Cover;

import java.util.List;

public class ListSanAdapter extends RecyclerView.Adapter<ListSanAdapter.ListsanViewHolder>{
    private List<San> listSan;
    private Context context;
    private ListSanFragment fragment;
    private TextView tvTenSan, tvLoaiSan, tvGiaSan;
    private ImageView imgSan;
    private ITFOnItenClick itfOnItenClick;


    public ListSanAdapter(Context context, List<San> listSan, ITFOnItenClick itfOnItenClick) {
        this.context = context;
        this.listSan = listSan;
        this.itfOnItenClick = itfOnItenClick;
    }

    @NonNull
    @Override
    public ListsanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_san_layout, parent, false);
        return new ListsanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListsanViewHolder holder, int position) {
        San san = listSan.get(position);
        holder.tvTenSan.setText("Tên Sân: "+ san.tenSan);
        holder.tvLoaiSan.setText("Loại Sân: "+ san.loaiSan);
        holder.tvGiaSan.setText("Giá sân: "+ san.giaSan+ "VND");
        try {
            holder.imgSan.setImageBitmap(Cover.ByteToBitmap(san.anhSan));
        }catch (Exception e){

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itfOnItenClick.onItemClick(san);
//                Toast.makeText(context, "Layout click", Toast.LENGTH_SHORT).show();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                CaSanFragment caSanFragment = new CaSanFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_item_san,caSanFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSan.size();
    }

    public class ListsanViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTenSan, tvLoaiSan, tvGiaSan;
        private ImageView imgSan;
        public CardView cv;
        public LinearLayout layout;
        public int position;

        public ListsanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSan = itemView.findViewById(R.id.text_ten_san);
            tvLoaiSan = itemView.findViewById(R.id.text_loai_san);
            tvGiaSan = itemView.findViewById(R.id.text_gia_san);
            imgSan = itemView.findViewById(R.id.img_san);
            layout = itemView.findViewById(R.id.layout_item_san);
//            cv = (CardView) itemView;
        }
    }


}
