package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.entity.CumSan;

import java.util.ArrayList;
import java.util.List;

public class CumSanDAO {
    private SQLiteDatabase db;

    public CumSanDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(CumSan obj){
        ContentValues values = new ContentValues();
        values.put("chuSan",obj.chuSan);
        values.put("diaChi",obj.diaChi);
        values.put("tenCumSan",obj.tenCumSan);
        return db.insert("CumSan",null,values);
    }
    public int update(CumSan obj){
        try {
            ContentValues values = new ContentValues();
            values.put("chuSan",obj.chuSan);
            values.put("diaChi",obj.diaChi);
            values.put("tenCumSan",obj.tenCumSan);
            return db.update("CumSan",values,"maCumSan=?",new String[]{String.valueOf(obj.maCumSan)});
        }catch (Exception ex){
        }
        return -1;
    }

    public int delete(String id){
        return db.delete("CumSan","maCumSan=?",new String[]{id});
    }
    public List<CumSan> getAll(){
        String sql = "SELECT * FROM CumSan";
        return getData(sql);
    }


    public List<CumSan> getAll1(){
        String sql = "SELECT * FROM CumSan WHERE maCumSan > 4 INNER JOIN San ON CumSan.maCumSan = San.maCumSan WHERE San.maCumSan IS NOT NULL";
        return getData(sql);
    }
    public List<CumSan> getCSByChuSan(String chuSan){
        String sql = "SELECT * FROM CumSan WHERE chuSan = ?";
        return getData(sql, chuSan);
    }


    public CumSan getCumSanBySan(String maSan){
        String sql = "SELECT * FROM CumSan INNER JOIN San ON CumSan.maCumSan = San.maCumSan WHERE San.maSan=?";
        return getData(sql, maSan).get(0);
    }


    public CumSan getID(String id){
        String sql = "SELECT * FROM CumSan WHERE maCumSan=?";
        List<CumSan> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<CumSan> getData(String sql ,String...selectionArgs){
        List<CumSan> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            CumSan obj = new CumSan();
            obj.maCumSan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maCumSan")));
            obj.chuSan = cursor.getString(cursor.getColumnIndex("chuSan"));
            obj.diaChi = cursor.getString(cursor.getColumnIndex("diaChi"));
            obj.tenCumSan = cursor.getString(cursor.getColumnIndex("tenCumSan"));
            list.add(obj);
        }
        return list;
    }
}
