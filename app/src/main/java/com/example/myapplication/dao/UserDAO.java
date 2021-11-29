package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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


    public int insert(User user){
        ContentValues values = new ContentValues();
        values.put("taiKhoan", user.taiKhoan);
        values.put("hoTen", user.hoTen);
        values.put("matKhau", user.matKhau);
        values.put("phanQuyen", user.phanQuyen);
        values.put("hinh", user.hinhAnh);

        return (int) db.insert(TABLE_NAME, null, values);
    }
    public int update(User user){
        ContentValues values = new ContentValues();
        values.put("hoTen", user.hoTen);
        values.put("matKhau", user.matKhau);
        values.put("phanQuyen", user.phanQuyen);
        values.put("hinh", user.hinhAnh);
        return db.update(TABLE_NAME, values, "taiKhoan=?", new String[]{user.taiKhoan});
    }

    public int delete(String id){
        return db.delete("User","taiKhoan=?",new String[]{id});
    }

    public User getUser(String taiKhoan){
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE taiKhoan=?";
        return getData(sql, taiKhoan).get(0);
    }

    

    public boolean checkLogin(String taiKhoan, String pass){
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE taiKhoan=? AND matKhau=?";
        try {
            List<User> list = getData(sql, taiKhoan, pass);
            if (list.size() > 0 && list != null){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

//    public List<User> getChuSan(){
//        String sql = "SELECT * FROM "+TABLE_NAME+ " WHERE phanQuyen = 'CS'";
//        return getData(sql);
//    }
//
//    public List<User> getNguoiThue(){
//        String sql = "SELECT * FROM "+TABLE_NAME+ " WHERE phanQuyen = 'NT'";
//        return getData(sql);
//    }
    public List<User> getPhanQuyen(String PQ){
        String sql = "SELECT * FROM "+TABLE_NAME+ " WHERE phanQuyen =?";
        return getData(sql,PQ);
    }

    public List<User> seachUser(String TK,String PQ){
        String sql = "SELECT * FROM "+TABLE_NAME+ " WHERE taiKhoan =? AND phanQuyen =?";
        return getData(sql,TK,PQ);
    }
    public List<User> seachUser1(String TK){
        String sql = "SELECT * FROM "+TABLE_NAME+ " WHERE taiKhoan '";
        return getData(sql,TK);
    }

    @SuppressLint("Range")
    private List<User> getData(String sql , String...selectionArgs){
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            User obj = new User();
            obj.taiKhoan = cursor.getString(cursor.getColumnIndex("taiKhoan"));
            obj.hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
            obj.matKhau = cursor.getString(cursor.getColumnIndex("matKhau"));
            obj.phanQuyen = cursor.getString(cursor.getColumnIndex("phanQuyen"));
            obj.hinhAnh = cursor.getBlob(cursor.getColumnIndex("hinh"));
            list.add(obj);
        }
        return list;

    }
}
