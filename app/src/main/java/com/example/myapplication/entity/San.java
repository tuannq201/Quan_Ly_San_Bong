package com.example.myapplication.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public class San implements Serializable {
    public int maSan, giaSan, maCumSan;
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
}
