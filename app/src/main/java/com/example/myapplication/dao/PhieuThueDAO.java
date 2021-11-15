package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.example.myapplication.R;
import com.example.myapplication.database.DbHelper;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuThueDAO {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SQLiteDatabase db;
    Context context;
    public PhieuThueDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public long insert(PhieuThue obj){
        ContentValues values = new ContentValues();
        values.put("nguoiThue", obj.nguoiThue);
        values.put("maSan",String.valueOf(obj.maSan));
        values.put("ngayThue",obj.ngayThue);
        values.put("caThue",obj.caThue);
        values.put("tienSan",String.valueOf(obj.tienSan));
        values.put("danhGia", String.valueOf(obj.danhGia));
        values.put("sao", String.valueOf(obj.sao));
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
            values.put("danhGia", String.valueOf(obj.danhGia));
            values.put("sao", String.valueOf(obj.sao));
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


    public int soDanhGiaSan(String maSan){
        String sql = "SELECT * FROM PhieuThue WHERE maSan=? AND danhGia = 1";
        return getData(sql, maSan).size();
    }
    public float danhGiaSan(String maSan){
        String sql = "SELECT * FROM PhieuThue WHERE maSan=? AND danhGia = 1";
        List<PhieuThue> phieuThues = getData(sql, maSan);
        if (phieuThues.size() > 0){
            int soDanhGia = phieuThues.size();
            int soSao = 0;
            for (int i = 0;i<soDanhGia;i++){
                soSao += phieuThues.get(i).sao;
            }
            return (float) soSao / (float) soDanhGia;
        }
        return 10;
    }
    @SuppressLint("Range")
    public int thuNhap(String ngay){
        String sql = "SELECT SUM(tienSan) as thuNhapNgay FROM PhieuThue WHERE ngayThue =?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,new String[]{ngay});
        while (cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("thuNhapNgay"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

//    public int soDanhGiaCumSan(String maCumSan){
//        SanDAO sanDAO = new SanDAO(context);
//        List<San> sanList = sanDAO.getSanByCumSan(maCumSan);
//        int soSan = 0;
//        if (sanList.size() > 0){
//            for (int i = 0;i<soSan;i++){
//                if (danhGiaSan(String.valueOf(sanList.get(i).maSan)) <= 5){
//                    soSan++;
//                    soSao += danhGiaSan(String.valueOf(sanList.get(i).maSan));
//                }
//            }
//
//            return
//        }
//    }
    public float danhGiaCumSan(String maCumSan){
        SanDAO sanDAO = new SanDAO(context);
        List<San> sanList = sanDAO.getSanByCumSan(maCumSan);
        int soSan = 0;
        float soSao = 0;
        if (sanList.size() > 0){
            for (int i = 0;i<soSan;i++){
                if (danhGiaSan(String.valueOf(sanList.get(i).maSan)) <= 5){
                    soSan++;
                    soSao += danhGiaSan(String.valueOf(sanList.get(i).maSan));
                }
            }

            return soSao / (float) soSan;
        }
        return 10;
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
            trangThai.tienSan = phieuThue.tienSan;
            trangThai.ca = phieuThue.caThue;
            trangThai.ngay = phieuThue.ngayThue;
            trangThai.taiKhoan = phieuThue.nguoiThue;
            trangThai.color = Color.parseColor("#C6C4C4");
        }catch (Exception e){
            SanDAO sanDAO = new SanDAO(context);
            San san = sanDAO.getID(String.valueOf(maSan));
            trangThai.maSan = maSan;
            trangThai.ca = ca;
            trangThai.tienSan = san.giaSan;
            trangThai.ngay = ngay;
            trangThai.taiKhoan = "chưa thuê";
            trangThai.color = Color.parseColor("#ffffff");
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
