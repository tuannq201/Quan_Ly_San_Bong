package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.entity.KhuyenMai;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;

import java.util.ArrayList;
import java.util.List;

public class PhieuThueDAO {

    private SQLiteDatabase db;
    KhuyenMaiDAO khuyenMaiDAO;
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
        values.put("phanHoi", obj.phanHoi);
        values.put("soKM", String.valueOf(obj.soKM));
        return db.insert("PhieuThue",null,values);
    }
    public int update(PhieuThue obj){
        ContentValues values = new ContentValues();
        values.put("nguoiThue",obj.nguoiThue);
        values.put("maSan",String.valueOf(obj.maSan));
        values.put("ngayThue",obj.ngayThue);
        values.put("caThue",obj.caThue);
        values.put("tienSan",String.valueOf(obj.tienSan));
        values.put("danhGia", String.valueOf(obj.danhGia));
        values.put("sao", String.valueOf(obj.sao));
        values.put("phanHoi", obj.phanHoi);
        values.put("soKM", String.valueOf(obj.soKM));
        return db.update("PhieuThue",values,"maPT=?",new String[]{String.valueOf(obj.maPT)});
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
        try {
            return getData(sql, maSan).size();
        }catch (Exception e){
            return 1;
        }

    }
    @SuppressLint("Range")
    public int soSaoSan(String maSan){
        String sql = "SELECT SUM(sao) as soSao FROM PhieuThue WHERE maSan=? AND danhGia = 1";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,new String[]{maSan});
        while (cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soSao"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

    public int soDanhGiaCumSan(String maCS){
        String sql = "SELECT * FROM PhieuThue " +
                "INNER JOIN San " +
                "ON PhieuThue.maSan = San.maSan " +
                "WHERE San.maCumSan=? " +
                "AND PhieuThue.danhGia = 1";
        return getData(sql, maCS).size();
    }
    public int soLuotThueCumSan(String maCS){
        String sql = "SELECT * FROM PhieuThue INNER JOIN San ON PhieuThue.maSan = San.maSan WHERE San.maCumSan=?";
        return getData(sql, maCS).size();
    }

    @SuppressLint("Range")
    public int soSaoCumSan(String maCS){
        String sql = "SELECT SUM(sao) as soSao FROM PhieuThue INNER JOIN San ON PhieuThue.maSan = San.maSan WHERE San.maCumSan=? AND PhieuThue.danhGia = 1";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,new String[]{maCS});
        while (cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soSao"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
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


    @SuppressLint("Range")
    public int AllTienThueNT(String nguoiThue){
        String sql = "SELECT SUM(tienSan) as allTienThueNT FROM PhieuThue WHERE nguoiThue =?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,new String[]{nguoiThue});
        while (cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("allTienThueNT"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }


    public List<PhieuThue> getPhieuThueCumSan(String maCS){
        String sql = "SELECT * FROM PhieuThue INNER JOIN San ON PhieuThue.maSan = San.maSan WHERE San.maCumSan=?";
        return getData(sql, maCS);
    }
    public List<PhieuThue> getPhieuThueSan(String maSan){
        String sql = "SELECT * FROM PhieuThue WHERE maSan=?";
        return getData(sql, maSan);
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

    public int getByDate(String maNT, String date){
        String sql = "SELECT * FROM PhieuThue WHERE nguoiThue=? AND ngayThue>?";
        return getData(sql, maNT, date).size();
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
            trangThai.maPT = phieuThue.maPT;
            trangThai.color = Color.parseColor("#C6C4C4");
            trangThai.soKM = phieuThue.soKM;
        }catch (Exception e){
            SanDAO sanDAO = new SanDAO(context);
            khuyenMaiDAO = new KhuyenMaiDAO(context);
            San san = sanDAO.getID(String.valueOf(maSan));
            try {
                KhuyenMai khuyenMai = khuyenMaiDAO.getKhuyenMai(maSan, ngay, ca);
                trangThai.soKM = khuyenMai.soKM;
            }catch (Exception e1){
                if (ca.equals("4")||ca.equals("5")||ca.equals("6")||ca.equals("7")){
                    trangThai.soKM = 20;
                }else {
                    trangThai.soKM = 0;
                }
            }
            trangThai.maSan = maSan;
            trangThai.ca = ca;
            trangThai.tienSan = san.giaSan;
            trangThai.ngay = ngay;
            trangThai.taiKhoan = "Chưa Thuê";
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
            obj.danhGia = Integer.parseInt(cursor.getString(cursor.getColumnIndex("danhGia")));
            obj.sao = Integer.parseInt(cursor.getString(cursor.getColumnIndex("sao")));
            obj.phanHoi = cursor.getString(cursor.getColumnIndex("phanHoi"));
            obj.soKM = Integer.parseInt(cursor.getString(cursor.getColumnIndex("soKM")));
            list.add(obj);
        }
        return list;
    }
}
