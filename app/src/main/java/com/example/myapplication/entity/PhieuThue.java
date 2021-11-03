package com.example.myapplication.entity;

import android.graphics.Bitmap;

import java.util.Date;

public class PhieuThue {
    public int maPT,maSan,maNT,caThue,tienSan;
    public Date ngayThue;
    public Bitmap anhSan;

    public PhieuThue() {
    }

    public PhieuThue(int maPT, int maSan, int maNT, int caThue, int tienSan, Date ngayThue, Bitmap anhSan) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.maNT = maNT;
        this.caThue = caThue;
        this.tienSan = tienSan;
        this.ngayThue = ngayThue;
        this.anhSan = anhSan;
    }
}