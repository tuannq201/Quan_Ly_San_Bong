package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String TABLE_NAME = "User";
    private SQLiteDatabase db;
    public static List<User> listUser = new ArrayList<>();

    public UserDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

//    public int insert(User user){
//        ContentValues values = new ContentValues();
//        values.put("taiKhoan", user.getTaiKhoan());
//        values.put("ten", user.getTen());
//        values.put("matKhau", user.getMatKhau());
//        values.put("phanQuyen", user.getPhanQuyen());
//        values.put("hinh", user.getHinhAnh());
//    }
}
