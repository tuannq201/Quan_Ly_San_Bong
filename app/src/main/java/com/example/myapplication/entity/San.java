package com.example.myapplication.entity;

import android.graphics.Bitmap;

public class San {
    public int maSan,giaSan,loaiSan;
    public String tenSan,taiKhoan, tenChuSan;
    public byte[] anhSan;

    public San(){

    }

    public San(int maSan, int giaSan, int loaiSan, String tenSan, String taiKhoan, String tenChuSan, byte[] anhSan) {
        this.maSan = maSan;
        this.giaSan = giaSan;
        this.loaiSan = loaiSan;
        this.tenSan = tenSan;
        this.taiKhoan = taiKhoan;
        this.tenChuSan = tenChuSan;
        this.anhSan = anhSan;
    }
}
