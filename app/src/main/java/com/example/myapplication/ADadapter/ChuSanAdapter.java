package com.example.myapplication.ADadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.UI.admin.ChuSanFragment;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.User;

import java.util.ArrayList;

public class ChuSanAdapter extends ArrayAdapter<User> implements Filterable {
    private Context context;
    ChuSanFragment fragment;
    private ArrayList<User> lists;
    TextView tvTen,tvSDT,tvPass;
    ImageView imgUser;
    UserDAO userDAO;
    private ArrayList<User> listSeach;

    public ChuSanAdapter(Context context, ChuSanFragment fragment, ArrayList<User> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_chu_san,null);
        }
        userDAO = new UserDAO(context);
        final User item = lists.get(position);
        if (item != null) {
            imgUser = view.findViewById(R.id.imgChuSan);
            if (userDAO.getUser(item.taiKhoan).phanQuyen.equals("CS")) {
                if (item.hinhAnh != null){
                    byte[] hinh = item.hinhAnh;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                    imgUser.setImageBitmap(bitmap);
                }
            }
            tvTen = view.findViewById(R.id.tvTenCS);
            if (userDAO.getUser(item.taiKhoan).phanQuyen.equals("CS")) {
                tvTen.setText("Họ Và Tên: " + item.hoTen);
            }
            tvSDT = view.findViewById(R.id.tvSDT);
            if (userDAO.getUser(item.taiKhoan).phanQuyen.equals("CS")) {
                tvSDT.setText("Số điện thoại: " + item.taiKhoan);
            }
            tvPass = view.findViewById(R.id.tvPass);
            if (userDAO.getUser(item.taiKhoan).phanQuyen.equals("CS")) {
                tvPass.setText("Mật khẩu: ******");
            }
        }
        Animation animation = AnimationUtils
                .loadAnimation(getContext(), R.anim.anim_user);
        view.setAnimation(animation);
        return view;
    }
}
