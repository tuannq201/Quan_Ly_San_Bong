package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "QLSB";
    static final int dbVision = 2;
    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVision);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_User = "CREATE TABLE User" +
                "(taiKhoan TEXT PRIMARY KEY, " +
                "ten TEXT NOT NULL," +
                "matKhau TEXT NOT NULL," +
                "phanQuyen TEXT NOT NULL, " +
                "hinh BLOB )";
        db.execSQL(create_User);


        String create_San = "CREATE TABLE San" +
                "(maSan INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "tenSan TEXT NOT NULL, " +
                "diaChi TEXT NOT NULL, " +
                "giaSan INTEGER NOT NULL, " +
                "loaiSan INTEGER NOT NULL, " +
                "chuSan TEXT NOT NULL, " +
                "anhSan BLOB )";
        db.execSQL(create_San);

        String create_PhieuThue = "CREATE TABLE PhieuThue" +
                "(maPT INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maSan INTEGER NOT NULL, " +
                "nguoiThue TEXT NOT NULL, " +
                "caThue TEXT NOT NULL, " +
                "ngayThue TEXT NOT NULL, " +
                "tienSan INTEGER NOT NULL)";
        db.execSQL(create_PhieuThue);


        String INSERT_User = "Insert into User(taiKhoan,ten,matKhau,phanQuyen,hinh) values " +
                "('9999','Nhom 1 Dep Trai','123456','AD',null)," +
                "('7777','Nhom 1 Dep Trai','123456','NT',null)," +
                "('8888','Nhom 1 Dep Trai','123456','CS',null)";
        db.execSQL(INSERT_User);

        String INSERT_San1 = "Insert into San(tenSan,diaChi,giaSan,loaiSan,chuSan, anhSan) values " +
                "('san 1','Nhom 1 Dep Trai','123456',5,'8888', null)," +
                "('san 2','Nhom 1 Dep Trai','123456',7,'8888', null)," +
                "('san 3','Nhom 1 Dep Trai','123456',5,'8888', null)," +
                "('san 4','Nhom 1 Dep Trai','123456',7,'8888', null)," +
                "('san 5','Nhom 1 Dep Trai','123456',5,'8888', null)," +
                "('san 6','Nhom 1 Dep Trai','123456',7,'8888', null)," +
                "('san 7','Nhom 1 Dep Trai','123456',5,'8888', null)";
        db.execSQL(INSERT_San1);

        String INSERT_PT = "Insert into PhieuThue(maSan,nguoiThue,caThue,ngayThue,tienSan) values " +
                "(1,'7777','5','2021-16-11',9999)," +
                "(2,'7777','8','2021-16-11',9999)," +
                "(3,'7777','2','2021-16-11',9999)";
        db.execSQL(INSERT_PT);

//        String INSERT_San = "Insert into San(tenSan,diaChi,giaSan,loaiSan,chuSan,anhSan) values " +
//                "('5a','12 Au Co Da Nang',200000,5,'Nhom 1 Dep Trai',null)," +
//                "('5b','12 Au Co Da Nang',200000,5,'Nhom 1 Dep Trai',null)," +
//                "('5c','12 Au Co Da Nang',200000,5,'Nhom 1 Dep Trai',null)," +
//                "('5d','12 Au Co Da Nang',200000,5,'Nhom 1 Dep Trai',null)," +
//                "('5e','12 Au Co Da Nang',200000,5,'Nhom 1 Dep Trai',null)," +
//                "('5f','12 Au Co Da Nang',200000,5,'Nhom 1 Dep Trai',null)," +
//                "('7a','12 Au Co Da Nang',300000,7,'Nhom 1 Dep Trai',null)," +
//                "('7b','12 Au Co Da Nang',300000,7,'Nhom 1 Dep Trai',null)";
//        db.execSQL(INSERT_San);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists San");
        db.execSQL("drop table if exists PhieuThue");
        onCreate(db);
    }
}
