package com.example.myapplication.entity;

import android.graphics.Bitmap;

public class San {
    public int maSan,giaSan,loaiSan;
    public String tenSan,taiKhoan,diaChi;
    public byte[] anhSan;

    public San(){

    }

    public San(int maSan, String tenSan, int giaSan, int loaiSan, String taiKhoan, String diaChi, byte[] anhSan) {
        this.maSan = maSan;
        this.giaSan = giaSan;
        this.loaiSan = loaiSan;
        this.tenSan = tenSan;
        this.taiKhoan = taiKhoan;
        this.anhSan = anhSan;
        this.diaChi = diaChi;
    }
}
