package com.example.myapplication.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public class San implements Serializable {
    public int maSan, giaSan, maCumSan, soDanhGia, soSao;
    public String tenSan,loaiSan;
    public byte[] anhSan;

    public San(){
    }

    public San(int maSan, int giaSan, int maCumSan, String tenSan, String loaiSan, byte[] anhSan) {
        this.maSan = maSan;
        this.giaSan = giaSan;
        this.maCumSan = maCumSan;
        this.tenSan = tenSan;
        this.loaiSan = loaiSan;
        this.anhSan = anhSan;
    }

    public San(int maSan, int giaSan, int maCumSan, int soDanhGia, int soSao, String tenSan, String loaiSan, byte[] anhSan) {
        this.maSan = maSan;
        this.giaSan = giaSan;
        this.maCumSan = maCumSan;
        this.soDanhGia = soDanhGia;
        this.soSao = soSao;
        this.tenSan = tenSan;
        this.loaiSan = loaiSan;
        this.anhSan = anhSan;
    }
}
