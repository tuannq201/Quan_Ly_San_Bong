package com.example.myapplication.adapter.nguoi_thue_adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myapplication.R;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.itf.ITFClickPhieuThue;
import com.example.myapplication.util.BlurTransformation;
import com.example.myapplication.util.Cover;

import java.util.List;

public class SDTAdapter extends ArrayAdapter<PhieuThue> {
    Context context;
    List<PhieuThue> list;
    CumSanDAO cumSanDAO;
    SanDAO sanDAO;
    TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_danhGia;
    RatingBar rb;
    ImageView iv, iv_start_rating, iv_del;
    ITFClickPhieuThue itfClickPhieuThue;
    public SDTAdapter(@NonNull Context context, List<PhieuThue> list, ITFClickPhieuThue itfClickPhieuThue) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.itfClickPhieuThue = itfClickPhieuThue;
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
            tv_2 = v.findViewById(R.id.tv_diaChi_ddk);
            tv_3 = v.findViewById(R.id.tv_loai_san_ddk);
            tv_4 = v.findViewById(R.id.tv_gia_san_ddk);
            tv_5 = v.findViewById(R.id.tv_thoi_gian_ddk);
            iv = v.findViewById(R.id.iv_san_ddk);
            tv_danhGia = v.findViewById(R.id.tv_danh_gia_ddk);
            rb = v.findViewById(R.id.rb_danhGia_nt);
            iv_del = v.findViewById(R.id.iv_xoa_sdt);
            iv_start_rating = v.findViewById(R.id.iv_open_rating_nt);

            try {
                tv_1.setText("Sân "+cumSanDAO.getCumSanBySan(String.valueOf(phieuThue.maSan)).tenCumSan+" ,"+sanDAO.getID(String.valueOf(phieuThue.maSan)).tenSan);
                tv_2.setText("Địa chỉ: "+cumSanDAO.getCumSanBySan(String.valueOf(phieuThue.maSan)).diaChi);
                tv_3.setText("Loại sân: sân "+sanDAO.getID(String.valueOf(phieuThue.maSan)).loaiSan);
                tv_4.setText("Giá: "+ Cover.IntegerToVnd(phieuThue.tienSan)+"vnđ (-"+phieuThue.soKM+"%)");
                tv_5.setText("Thời gian: "+Cover.caToTime(phieuThue.caThue)+" ,"+phieuThue.ngayThue);
            }catch (Exception e){
                tv_1.setText("hhhhhhhh");
                tv_2.setText("hhhhhhhh");
                tv_3.setText("hhhhhhhh");
                tv_4.setText("hhhhhhhh");
            }
            if (phieuThue.danhGia != 1){
                //chưa đánh giá
                rb.setVisibility(View.GONE);
                tv_danhGia.setVisibility(View.GONE);
                iv_start_rating.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(1)
                        .playOn(iv_start_rating);
            }else {
                iv_start_rating.setVisibility(View.GONE);
                rb.setVisibility(View.VISIBLE);
                tv_danhGia.setVisibility(View.VISIBLE);
                rb.setRating((float) phieuThue.sao);
            }

            iv_del.setOnClickListener(view -> {
                itfClickPhieuThue.OnClick(phieuThue);
            });


        }return v;
    }


}
