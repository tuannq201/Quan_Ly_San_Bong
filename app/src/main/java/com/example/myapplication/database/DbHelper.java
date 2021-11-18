package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{
    static final String dbName = "QLSB";
    static final int dbVision = 4;
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
                "maCumSan INTEGER NOT NULL ," +
                "anhSan BLOB )";
        db.execSQL(create_San);

        String create_PhieuThue = "CREATE TABLE PhieuThue" +
                "(maPT INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maSan INTEGER NOT NULL, " +
                "nguoiThue TEXT NOT NULL, " +
                "caThue TEXT NOT NULL, " +
                "ngayThue DATE NOT NULL, " +
                "tienSan INTEGER NOT NULL, " +
                "danhGia INTEGER, " +//:0-chưa đánh giá; 1-đã đánh giá
                "sao INTEGER)";// 1-5 sao
        db.execSQL(create_PhieuThue);

//        String create_KhuyenMai = "CREATE TABLE KhuyenMai" +
//                "(maKM INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "cumSan TEXT NOT NULL, " +
//                "dieuKien TEXT, " +
//                "khuyenMai IMTEGER NOT NULL, " +
//                "apDung TEXT)";
//        db.execSQL(create_KhuyenMai);

        String INSERT_User = "Insert into User(taiKhoan,hoTen,matKhau,phanQuyen,hinh) values " +
                "('0999999999','Nhom 1 Dep Trai','123456','AD',null)," +
                "('0777777777','Nhom 1 Dep Trai','123456','NT',null)," +
                "('0777777771','Nhom 1 Dep Trai','123456','NT',null)," +
                "('0777777772','Nhom 1 Dep Trai','123456','NT',null)," +
                "('0777777773','Nhom 1 Dep Trai','123456','NT',null)," +
                "('0777777774','Nhom 1 Dep Trai','123456','NT',null)," +
                "('0777777775','Nhom 1 Dep Trai','123456','NT',null)," +
                "('0777777776','Nhom 1 Dep Trai','123456','NT',null)," +
                "('0888888888','Nhom 1 Dep Trai','123456','CS',null)," +
                "('0333333333','Nhom 1 Dep Trai','123456','CS',null)," +
                "('0333333331','Nhom 1 Dep Trai','123456','CS',null)," +
                "('0333333332','Nhom 1 Dep Trai','123456','CS',null)," +
                "('0333333334','Nhom 1 Dep Trai','123456','CS',null)," +
                "('0333333335','Nhom 1 Dep Trai','123456','CS',null)," +
                "('0333333336','Nhom 1 Dep Trai','123456','CS',null)," +
                "('0555555555','Nhom 1 Dep Trai','123456','CS',null)";
        db.execSQL(INSERT_User);

        String INSERT_San1 = "Insert into San(tenSan,giaSan,loaiSan, maCumSan, anhSan) values " +
                "('san 1','150000',5, 1,null)," +
                "('san 2','120000',7, 1,null)," +
                "('san 3','120000',5, 1,null)," +
                "('san 4','150000',7, 2,null)," +
                "('san 5','120000',5, 2,null)," +
                "('san 6','200000',7, 2,null)," +
                "('san 7','150000',5, 3,null)," +
                "('san 8','200000',7, 3,null)," +
                "('san 9','250000',5, 4,null)," +
                "('san 10','200000',7, 4,null)," +
                "('san 11','150000',5, 4,null)," +
                "('san 12','120000',7, 6,null)," +
                "('san 13','250000',5, 6,null)," +
                "('san 14','250000',7, 6,null)," +
                "('san 15','150000',5, 7,null)," +
                "('san 16','220000',7, 7,null)," +
                "('san 17','220000',5, 7,null)," +
                "('san 18','220000',7, 7,null)," +
                "('san 19','150000',5, 7,null)";
        db.execSQL(INSERT_San1);

        String INSERT_CumSan = "Insert into CumSan(tenCumSan,diaChi,chuSan) values " +
                "('Sân Bóng Đá Chuyên Việt - Hoà Khánh','151 Âu Cơ, Hoà Khánh Bắc, Liên Chiểu, Đà Nẵng','0888888888')," +
                "('Sân Bóng Manchester United','59 Đ. Ngô Thì Nhậm, Hoà Khánh Nam, Liên Chiểu, Đà Nẵng','0888888888')," +
                "('Sân Bóng đá 360','911 Nguyễn Lương Bằng, Hoà Hiệp Nam, Liên Chiểu, Đà Nẵng','0888888888')," +
                "('Sân Bóng Liên Chiểu - Nhật Nga','522 Nguyễn Lương Bằng, Hoà Hiệp Nam, Liên Chiểu, Đà Nẵng','0333333331')," +
                "('Sân bóng nhân tạo Nam Cao','347X+C74, Hoà Khánh Nam, Liên Chiểu, Đà Nẵng','0888888888')," +
                "('Sân Bóng Ngọc Thạch','K207 Đ. Phạm Như Xương, Hoà Khánh Nam, Liên Chiểu, Đà Nẵng','0333333331')," +
                "('Sân bóng An Trung','An Trung 3, An Hải Tây, Sơn Trà, Đà Nẵng','0888888888')," +
                "('Sân bóng đá T20','Mỹ Khê 4, Phước Mỹ, Sơn Trà, Đà Nẵng','0888888888')," +
                "('Sân bóng Harmony','Phạm Văn Đồng, An Hải Bắc, Sơn Trà, Đà Nẵng','0888888888')," +
                "('Sân bóng đá Chuyên Việt','98 Tiểu La, Hòa Thuận Đông, Hải Châu, Đà Nẵng','0888888888')," +
                "('Sân bóng đá Trang Hoàng','86 Duy Tân, Hòa Thuận Nam, Hải Châu, Đà Nẵng','0333333336')," +
                "('Sân bóng đá Duy Tân','Hòa Thuận Đông, Hải Châu, Đà Nẵng','0888888888')," +
                "('Sân Bóng đá An Phúc 2','409 Trưng Nữ Vương, Hòa Thuận Nam, Hải Châu, Đà Nẵng','0333333336')," +
                "('Sân bóng đá Mỹ Nhật Quang','498 Nguyễn Hữu Thọ, Khuê Trung, Cẩm Lệ, Đà Nẵng','0888888888')," +

                "('Sân Bóng Đá Mini Việt Hàn','Hoà Hải, Ngũ Hành Sơn, Đà Nẵng','0888888888')," +
                "('Sân Bóng Đá Thép Việt','Đ. Nghiêm Xuân Yêm, Khuê Mỹ, Ngũ Hành Sơn, Đà Nẵng','0888888888')," +
                "('Sân bóng đá Minh Hà','Bắc Mỹ Phú, Ngũ Hành Sơn, Đà Nẵng','0888888888')," +
                "('Sân bóng đá mini Bế Văn Đàn','Bế Văn Đàn, Chính Gián, Thanh Khê, Đà Nẵng','0333333336')," +
                "('Sân bóng đá An Hà 1','243 Trường Chinh, An Khê, Thanh Khê, Đà Nẵng','0888888888')," +
                "('Sân Bóng đá An Phúc 1','303 Trưng Nữ Vương, Hòa Thuận Nam, Hải Châu, Đà Nẵng','0333333336')," +

                "('Sân Bóng Đá Hòa Hán','Đ. Tôn Đản, Hoà Thọ Tây, Cẩm Lệ, Đà Nẵng','0888888888')," +
                "('Sân Bóng Đá Mini Tuấn Nhàn','Nguyễn Văn Tạo, Hoà An, Cẩm Lệ, Đà Nẵng','0888888888')," +

                "('cụ15','Liên Chiểu - Đà Nẵng','0888888888')";
        db.execSQL(INSERT_CumSan);


        String INSERT_PT = "Insert into PhieuThue(maSan,nguoiThue,caThue,ngayThue,tienSan, danhGia, sao) values " +
                "(1,'0777777777','1','11-11-2021',100000, 1, 5)," +
                "(5,'0777777773','8','11-11-2021',100000, 1, 4)," +
                "(3,'0777777777','5','11-11-2021',100000, 1, 5)," +
                "(5,'0777777777','11','11-11-2021',100000, 1, 4)," +
                "(1,'0777777777','1','12-11-2021',100000, 1, 5)," +
                "(5,'0777777771','8','12-11-2021',100000, 1, 4)," +
                "(3,'0777777771','5','13-11-2021',100000, 1, 5)," +
                "(1,'0777777771','11','15-11-2021',100000, 1, 4)," +
                "(1,'0777777774','1','17-11-2021',100000, 1, 5)," +
                "(5,'0777777774','8','17-11-2021',100000, 1, 4)," +
                "(3,'0777777775','5','17-11-2021',100000, 1, 5)," +
                "(5,'0777777775','11','19-11-2021',100000, 1, 4)," +
                "(7,'0777777775','2','19-11-2021',100000, 1, 4)";
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
