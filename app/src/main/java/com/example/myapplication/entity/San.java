package com.example.myapplication.entity;

import android.graphics.Bitmap;

public class San {
    public int maSan, giaSan, maCumSan;
    public String tenSan, taiKhoan,loaiSan;
    public byte[] anhSan;

    public San(){

    }

    public San(int maSan, int giaSan, String loaiSan, String tenSan, String taiKhoan, int maCumSan, byte[] anhSan) {
        this.maSan = maSan;
        this.giaSan = giaSan;
        this.loaiSan = loaiSan;
        this.tenSan = tenSan;
        this.taiKhoan = taiKhoan;
        this.maCumSan = maCumSan;
        this.anhSan = anhSan;
    }
}
