package com.example.myapplication.ADadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.UI.admin.AdminActivity;
import com.example.myapplication.UI.admin.ChuSanFragment;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.CaSan;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.User;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ChuSanAdapter extends ArrayAdapter<User> {
    private Context context;
    ChuSanFragment fragment;
    private ArrayList<User> lists;
    TextView tvTen,tvSDT,tvPass;
    ImageView imgUser;

    List<CaSan> listCaSan;

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
        UserDAO userDAO = new UserDAO(context);
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
                tvPass.setText("Mật khẩu: " + item.matKhau);
            }

        }

        return view;
    }
}
