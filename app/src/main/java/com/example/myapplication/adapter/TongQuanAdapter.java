package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myapplication.R;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.util.ArrayList;
import java.util.List;

public class TongQuanAdapter extends ArrayAdapter<TrangThai> {

    Context context;
    List<PhieuThue> list;
    ArrayList<TrangThai> list1;
    CumSanDAO cumSanDAO;
    SanDAO sanDAO;
    TextView tv_1, tv_2, tv_3;

    public TongQuanAdapter(@NonNull Context context, ArrayList<TrangThai> list1) {
        super(context,0,list1);
        this.context = context;
        this.list1 = list1;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_tong_quan, null);
        }
        final TrangThai item = list1.get(position);
        sanDAO = new SanDAO(context);
        cumSanDAO = new CumSanDAO(context);
        if (item!= null){
            tv_1 = v.findViewById(R.id.tvCaSan);
            tv_2 = v.findViewById(R.id.tvGiaSan);
            tv_3 = v.findViewById(R.id.tvTrangThaiTQ);
            try {
                tv_1.setText(""+Cover.caToTime(item.ca));
                int km = item.soKM;
                int thanhToan = item.tienSan - (item.tienSan * km / 100);
                tv_2.setText(""+ Cover.IntegerToVnd(thanhToan)+"vnđ ");
                tv_3.setText(""+item.taiKhoan);
                if (item.taiKhoan.contains("0")){
                    tv_3.setText("Đã Thuê");
                    tv_3.setTextColor(Color.GREEN);
                }else if(item.taiKhoan.equals("Chưa Thuê")){
                    tv_3.setText("Chưa Thuê");
                    tv_3.setTextColor(Color.RED);
                }else {
                    tv_3.setText("Giữ sân");
                    tv_3.setTextColor(Color.BLUE);
                }
            }catch (Exception e){
                tv_1.setText("hhhhhhhh");
                tv_2.setText("hhhhhhhh");
                tv_3.setText("hhhhhhhh");
            }
        }return v;
    }
}
