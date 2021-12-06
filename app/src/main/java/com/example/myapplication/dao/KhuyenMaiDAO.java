package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.entity.KhuyenMai;
import com.example.myapplication.entity.PhieuThue;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO {
    private SQLiteDatabase db;
    Context context;
    public KhuyenMaiDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }
    public long insert(KhuyenMai obj){
        ContentValues values = new ContentValues();
        values.put("soKM", String.valueOf(obj.soKM));
        values.put("maSan",String.valueOf(obj.maSan));
        values.put("caThue",obj.ca);
        values.put("ngayThue",obj.ngay);
        return db.insert("KhuyenMai",null,values);
    }
    public int update(KhuyenMai obj){
        ContentValues values = new ContentValues();
        values.put("maID", String.valueOf(obj.maID));
        values.put("soKM", String.valueOf(obj.soKM));
        values.put("maSan",String.valueOf(obj.maSan));
        values.put("caThue",obj.ca);
        values.put("ngayThue",obj.ngay);
        return db.update("KhuyenMai",values,"maID=?",new String[]{String.valueOf(obj.maID)});
    }
    public int delete(String id){
        return db.delete("KhuyenMai","maID=?",new String[]{id});
    }
    public KhuyenMai getKhuyenMai(int maSan, String ngay, String ca){
        String sql = "SELECT * FROM KhuyenMai WHERE maSan=? AND ngayThue=? AND caThue=? ";
        return getData(sql, String.valueOf(maSan), ngay, ca).get(0);
    }
    @SuppressLint("Range")
    private List<KhuyenMai> getData(String sql , String...selectionArgs){
        List<KhuyenMai> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            KhuyenMai obj = new KhuyenMai();
            obj.maID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maID")));
            obj.soKM = Integer.parseInt(cursor.getString(cursor.getColumnIndex("soKM")));
            obj.maSan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSan")));
            obj.ca = cursor.getString(cursor.getColumnIndex("caThue"));
            obj.ngay = cursor.getString(cursor.getColumnIndex("ngayThue"));
            list.add(obj);
        }
        return list;
    }
}
