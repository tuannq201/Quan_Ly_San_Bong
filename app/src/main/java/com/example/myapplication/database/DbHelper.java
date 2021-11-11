package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{
    static final String dbName = "QLSB";
    static final int dbVision = 2;
    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVision);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_CumSan = "CREATE TABLE CumSan"+
                "(maCumSan INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "chuSan TEXT NOT NULL, " +
                "diaChi TEXT NOT NULL, " +
                "tenCumSan TEXT )";

        db.execSQL(create_CumSan);

        String create_User = "CREATE TABLE User" +
                "(taiKhoan TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL," +
                "phanQuyen TEXT NOT NULL, " +
                "hinh BLOB )";
        db.execSQL(create_User);


        String create_San = "CREATE TABLE San" +
                "(maSan INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "tenSan TEXT NOT NULL, " +
                "giaSan INTEGER NOT NULL, " +
                "loaiSan INTEGER NOT NULL, " +
                "chuSan TEXT NOT NULL, " +
                "maCumSan INTEGER NOT NULL ," +
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


        String INSERT_User = "Insert into User(taiKhoan,hoTen,matKhau,phanQuyen,hinh) values " +
                "('9999','Nhom 1 Dep Trai','123456','AD',null)," +
                "('7777','Nhom 1 Dep Trai','123456','NT',null)," +
                "('8888','Nhom 1 Dep Trai','123456','CS',null)";
        db.execSQL(INSERT_User);

        String INSERT_San1 = "Insert into San(tenSan,giaSan,loaiSan,chuSan, maCumSan, anhSan) values " +
                "('san 1','150000',5,'8888', '1',null)," +
                "('san 2','120000',7,'8888', '1',null)," +
                "('san 3','120000',5,'8888', '1',null)," +
                "('san 4','150000',7,'8888', '2',null)," +
                "('san 5','120000',5,'8888', '2',null)," +
                "('san 6','200000',7,'8888', '2',null)," +
                "('san 1','150000',5,'8888', '3',null)," +
                "('san 2','200000',7,'8888', '3',null)," +
                "('san 3','250000',5,'8888', '4',null)," +
                "('san 4','200000',7,'8888', '4',null)," +
                "('san 5','150000',5,'8888', '4',null)," +
                "('san 6','120000',7,'8888', '6',null)," +
                "('san 1','250000',5,'8888', '6',null)," +
                "('san 2','250000',7,'8888', '6',null)," +
                "('san 3','150000',5,'8888', '7',null)," +
                "('san 4','220000',7,'8888', '7',null)," +
                "('san 5','220000',5,'8888', '7',null)," +
                "('san 6','220000',7,'8888', '7',null)," +
                "('san 7','150000',5,'8888', '7',null)";
        db.execSQL(INSERT_San1);

        String INSERT_CumSan = "Insert into CumSan(tenCumSan,diaChi,chuSan) values " +
                "('cụm sân 1','Cẩm Lệ - Đà Nẵng','8888')," +
                "('cụm sân 2','Liên Chiểu - Đà Nẵng','8888')," +
                "('cụm sân 3','Hải Châu - Đà Nẵng','8888')," +
                "('cụm sân 4','Sơn Trà - Đà Nẵng','8888')," +
                "('cụm sân 5','Cẩm Lệ - Đà Nẵng','8888')," +
                "('cụm sân 6','Liên Chiểu - Đà Nẵng','8888')," +
                "('cụm sân 7','Hải Châu - Đà Nẵng','8888')," +
                "('cụm sân 8','Sơn Trà - Đà Nẵng','8888')," +
                "('cụm sân 9','Cẩm Lệ - Đà Nẵng','8888')," +
                "('cụm sân 10','Liên Chiểu - Đà Nẵng','8888')," +
                "('cụm sân 11','Hải Châu - Đà Nẵng','8888')," +
                "('cụm sân 12','Sơn Trà - Đà Nẵng','8888')," +
                "('cụm sân 13','Thanh Khê - Đà Nẵng','8888')," +
                "('cụm sân 14','Liên Chiểu - Đà Nẵng','8888')," +
                "('cụm sân 15','Liên Chiểu - Đà Nẵng','8888')";
        db.execSQL(INSERT_CumSan);
        String INSERT_PT = "Insert into PhieuThue(maSan,nguoiThue,caThue,ngayThue,tienSan) values " +
                "(1,'7777','1','2021-11-11',100000)," +
                "(5,'7777','8','2021-11-11',100000)," +
                "(3,'7777','5','2021-11-11',100000)," +
                "(5,'7777','11','2021-11-11',100000)," +
                "(7,'7777','2','2021-11-11',100000)";
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
        db.execSQL("drop table if exists CumSan");
        onCreate(db);
    }
}
