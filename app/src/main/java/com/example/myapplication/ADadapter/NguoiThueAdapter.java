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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.R;
import com.example.myapplication.UI.admin.ChuSanFragment;
import com.example.myapplication.UI.admin.NguoiThueFragment;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.User;

import java.util.ArrayList;

public class NguoiThueAdapter extends ArrayAdapter<User> {
    private Context context;
    NguoiThueFragment fragment;
    private ArrayList<User> lists;
    TextView tvTen,tvSDT,tvPass;
    ImageView imgUser;
    public NguoiThueAdapter(Context context, NguoiThueFragment fragment, ArrayList<User> lists) {
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
            view = inflater.inflate(R.layout.item_nguoi_thue,null);
        }
        UserDAO userDAO = new UserDAO(context);
        final User item = lists.get(position);
        if (item != null) {
            imgUser = view.findViewById(R.id.imgNguoiThue);
            if (userDAO.getUser(item.taiKhoan).phanQuyen.equals("NT")) {
                if (item.hinhAnh != null){
                    byte[] hinh = item.hinhAnh;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                    imgUser.setImageBitmap(bitmap);
                }
            }
            tvTen = view.findViewById(R.id.tvTenNT);
            if (userDAO.getUser(item.taiKhoan).phanQuyen.equals("NT")) {
                tvTen.setText("Họ Và Tên: " + item.hoTen);
            }
            tvSDT = view.findViewById(R.id.tvSDT);
            if (userDAO.getUser(item.taiKhoan).phanQuyen.equals("NT")) {
                tvSDT.setText("Số điện thoại: " + item.taiKhoan);
            }
            tvPass = view.findViewById(R.id.tvPass);
            if (userDAO.getUser(item.taiKhoan).phanQuyen.equals("NT")) {
                tvPass.setText("Mật khẩu: ******");
            }

        }
        Animation animation = AnimationUtils
                .loadAnimation(getContext(), R.anim.anim_user);
        view.setAnimation(animation);
        return view;
    }
}
