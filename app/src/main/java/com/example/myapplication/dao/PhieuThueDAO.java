package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.entity.CaSan;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.TrangThai;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuThueDAO {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SQLiteDatabase db;

    public PhieuThueDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuThue obj){
        ContentValues values = new ContentValues();
        values.put("nguoiThue", obj.nguoiThue);
        values.put("maSan",String.valueOf(obj.maSan));
        values.put("ngayThue",simpleDateFormat.format(obj.ngayThue));
        values.put("caThue",obj.caThue);
        values.put("tienSan",String.valueOf(obj.tienSan));
        return db.insert("PhieuThue",null,values);
    }
    public int update(PhieuThue obj){
        try {
            ContentValues values = new ContentValues();
            values.put("nguoiThue", obj.nguoiThue);
            values.put("maSan",String.valueOf(obj.maSan));
            values.put("ngayThue",simpleDateFormat.format(obj.ngayThue));
            values.put("caThue",obj.caThue);
            values.put("tienSan",String.valueOf(obj.tienSan));
            return db.update("PhieuThue",values,"maPT=?",new String[]{String.valueOf(obj.maPT)});
        }catch (Exception ex){
        }
        return -1;
    }
    public int delete(String id){
        return db.delete("PhieuThue","maPT=?",new String[]{id});
    }
    public List<PhieuThue> getAll(){
        String sql = "SELECT * FROM PhieuThue";
        return getData(sql);

    }
    public PhieuThue getID(String id){
        String sql = "SELECT * FROM PhieuThue WHERE maPT=?";
        List<PhieuThue> list = getData(sql,id);
        return list.get(0);
    }

    public List<PhieuThue> getPhieuByUser(String taiKhoan){
        String sql = "SELECT * FROM PhieuThue WHERE nguoiThue=?";
        List<PhieuThue> list = getData(sql,taiKhoan);
        return list;
    }

    public TrangThai checkTrangThai(int maSan, String ca, String ngay){
        String sql = "SELECT * FROM PhieuThue WHERE maSan=? AND caThue=? AND ngayThue=?";
        TrangThai trangThai = new TrangThai();
        try {
            PhieuThue phieuThue = getData(sql, String.valueOf(maSan), ca, ngay).get(0);
            trangThai.maSan = phieuThue.maSan;
            trangThai.ca = phieuThue.caThue;
            trangThai.ngay = phieuThue.ngayThue;
            trangThai.taiKhoan = "đã thuê";
        }catch (Exception e){
            trangThai.maSan = 0;
            trangThai.ca = "";
            trangThai.ngay = "";
            trangThai.taiKhoan = "chưa thuê";
        }
        return trangThai;
    }


    @SuppressLint("Range")
    private List<PhieuThue> getData(String sql ,String...selectionArgs){
        List<PhieuThue> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            PhieuThue obj = new PhieuThue();
            obj.maPT = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPT")));
            obj.maSan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSan")));
            obj.caThue = cursor.getString(cursor.getColumnIndex("caThue"));
            obj.tienSan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienSan")));
            obj.ngayThue = cursor.getString(cursor.getColumnIndex("ngayThue"));
            obj.nguoiThue = cursor.getString(cursor.getColumnIndex("nguoiThue"));

            list.add(obj);
        }
        return list;
    }
}
