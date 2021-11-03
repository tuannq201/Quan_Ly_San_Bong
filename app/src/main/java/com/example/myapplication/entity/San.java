package com.example.myapplication.entity;

public class San {
    public int maSan,giaSan,loaiSan;
    public String tenSan,taiKhoan;

    public San(){

    }

    public San(int maSan, String tenSan, int giaSan, int loaiSan, String taiKhoan) {
        this.maSan = maSan;
        this.giaSan = giaSan;
        this.loaiSan = loaiSan;
        this.tenSan = tenSan;
        this.taiKhoan = taiKhoan;
    }
}
