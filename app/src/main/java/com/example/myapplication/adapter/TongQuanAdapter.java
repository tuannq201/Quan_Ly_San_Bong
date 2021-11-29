package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TongQuanAdapter extends ArrayAdapter<PhieuThue> {

    Context context;
    List<PhieuThue> list;
    private ArrayList<TrangThai> list1;
    CumSanDAO cumSanDAO;
    SanDAO sanDAO;
    TextView tv_1, tv_2, tv_3;

    public TongQuanAdapter(@NonNull Context context, ArrayList<TrangThai> list1) {
        super(context,0);
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
        final PhieuThue phieuThue = list.get(position);
        final TrangThai item = list1.get(position);
        sanDAO = new SanDAO(context);
        cumSanDAO = new CumSanDAO(context);
        if (phieuThue!= null){
            tv_1 = v.findViewById(R.id.tvCaSan);
            tv_2 = v.findViewById(R.id.tvGiaSan);
            tv_3 = v.findViewById(R.id.tvTrangThaiTQ);
            try {
                tv_1.setText("Thời gian: "+Cover.caToTime(phieuThue.caThue)+" ,"+phieuThue.ngayThue);
                tv_2.setText("Giá: "+ Cover.IntegerToVnd(phieuThue.tienSan)+"vnđ (-"+Cover.KhuyenMai1(phieuThue.caThue)+"%)");
                tv_3.setText("Trạng Thái: "+item.taiKhoan);
                if (item.taiKhoan.contains("0")){
                    tv_3.setText("Đã được thuê");
                }
            }catch (Exception e){
                tv_1.setText("hhhhhhhh");
                tv_2.setText("hhhhhhhh");
                tv_3.setText("hhhhhhhh");
            }
        }return v;
    }
}
