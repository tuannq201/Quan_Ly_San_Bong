package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.util.Cover;

import java.util.ArrayList;
import java.util.Collections;
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
        values.put("maCumSan", obj.maCumSan);
        values.put("anhSan",obj.anhSan);
        return db.insert("San",null,values);
    }
    public int update(San obj){
        try {
            ContentValues values = new ContentValues();
            values.put("giaSan",String.valueOf(obj.giaSan));
            values.put("loaiSan",String.valueOf(obj.loaiSan));
            values.put("tenSan",obj.tenSan);
            values.put("maCumSan", obj.maCumSan);
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
//    public List<San> getAllByIDChuSan(String taiKhoan){
//        String sql = "SELECT * FROM San WHERE chuSan = ?";
//        return getData(sql, taiKhoan);
//    }

    public List<San> getSanByCumSan(String maCumSan){
        String sql = "SELECT * FROM San WHERE maCumSan=?";
        return getData(sql, maCumSan);
    }
    public String loaiSanCumSan(String maCS){
        List<San> sanList = getSanByCumSan(maCS);
        StringBuilder builder = new StringBuilder();
        if (sanList.size()>0){
            builder.append(sanList.get(0).loaiSan);
            for (int i=1; i<sanList.size();i++){
                if (!builder.toString().contains(sanList.get(i).loaiSan)){
                    builder.append(" ,"+sanList.get(i).loaiSan);
                }
            }
            return builder.toString();
        }

        return "";
    }
    public String giaCumSan(String maCS){
        List<San> sanList = getSanByCumSan(maCS);
        List<Integer> listGia = new ArrayList<>();
        for (int i=0;i<sanList.size();i++){
            listGia.add(sanList.get(i).giaSan);
        }
        Collections.sort(listGia);
        if (listGia.isEmpty()){
            return "nulll";
        }
        if (listGia.size()==1){
            return ""+Cover.IntegerToVnd(listGia.get(0));
        }
        return ""+ Cover.IntegerToVnd(listGia.get(0)) +"vnđ - "+Cover.IntegerToVnd(listGia.get(listGia.size()-1))+"vnđ";
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
            obj.loaiSan = cursor.getString(cursor.getColumnIndex("loaiSan"));
            obj.maCumSan = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maCumSan")));
            obj.anhSan = cursor.getBlob(cursor.getColumnIndex("anhSan"));
            list.add(obj);
        }
        return list;
    }
}
