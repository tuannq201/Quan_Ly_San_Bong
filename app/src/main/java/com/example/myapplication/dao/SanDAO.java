package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;

import java.util.ArrayList;
import java.util.List;

public class SanDAO {
    private SQLiteDatabase db;

    public SanDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(San obj){
        ContentValues values = new ContentValues();
        values.put("giaSan",String.valueOf(obj.giaSan));
        values.put("loaiSan",String.valueOf(obj.loaiSan));
        values.put("tenSan",obj.tenSan);
        values.put("diaChi",obj.diaChi);
        values.put("chuSan",obj.taiKhoan);
        values.put("anhSan",obj.anhSan);
        return db.insert("San",null,values);
    }
    public int update(San obj){
        try {
            ContentValues values = new ContentValues();
            values.put("giaSan",String.valueOf(obj.giaSan));
            values.put("loaiSan",String.valueOf(obj.loaiSan));
            values.put("tenSan",obj.tenSan);
            values.put("diaChi",obj.diaChi);
            values.put("chuSan",obj.taiKhoan);
            values.put("anhSan",obj.anhSan);
            return db.update("San",values,"maSan=?",new String[]{String.valueOf(obj.maSan)});
        }catch (Exception ex){
        }
        return -1;
    }
    public int delete(String id){
        return db.delete("San","maSan=?",new String[]{id});
    }
    public List<San> getAll(){
        String sql = "SELECT * FROM San";
        return getData(sql);
    }



    public San getID(String id){
        String sql = "SELECT * FROM San WHERE maSan=?";
        List<San> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<San> getData(String sql ,String...selectionArgs){
        List<San> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            San obj = new San();
            obj.maSan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSan")));
            obj.giaSan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaSan")));
            obj.tenSan = cursor.getString(cursor.getColumnIndex("tenSan"));
            obj.diaChi = cursor.getString(cursor.getColumnIndex("diaChi"));
            obj.loaiSan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("loaiSan")));
            obj.taiKhoan =cursor.getString(cursor.getColumnIndex("chuSan"));
            obj.anhSan = cursor.getBlob(cursor.getColumnIndex("anhSan"));
            list.add(obj);
        }
        return list;
    }
}
