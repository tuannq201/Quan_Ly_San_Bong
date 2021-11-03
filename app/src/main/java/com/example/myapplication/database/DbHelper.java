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
                "hinh BLOB NOT NULL)";
        db.execSQL(create_User);


        String create_San = "CREATE TABLE San" +
                "(ma INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "ten TEXT NOT NULL, " +
                "diaChi TEXT NOT NULL, " +
                "giaThue INTEGER NOT NULL, " +
                "loaiSan INTEGER NOT NULL, " +
                "chuSan TEXT NOT NULL, " +
                "hinh BLOB NOT NULL)";
        db.execSQL(create_San);

        String create_PhieuThue = "CREATE TABLE PhieuThue" +
                "(ma INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maSan INTEGER NOT NULL, " +
                "nguoiThue TEXT NOT NULL, " +
                "caThue INTEGER NOT NULL, " +
                "ngayThue DATE NOT NULL, " +
                "tienThue INTEGER NOT NULL)";
        db.execSQL(create_PhieuThue);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists San");
        db.execSQL("drop table if exists PhieuThue");
        onCreate(db);
    }
}
