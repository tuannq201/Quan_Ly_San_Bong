package com.example.myapplication.entity;

import java.util.Date;

public class PhieuThue {
    public int maPT,maSan,maNT,caThue,tienSan;
    public Date ngayThue;

    public PhieuThue() {
    }

    public PhieuThue(int maPT, int maSan, int maNT, int caThue, int tienSan, Date ngayThue) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.maNT = maNT;
        this.caThue = caThue;
        this.tienSan = tienSan;
        this.ngayThue = ngayThue;
    }
}
