package com.example.myapplication.entity;

import java.util.Date;

public class PhieuThue {
    public int maPT, maSan,caThue,tienSan;
    public String maNT;
    public Date ngayThue;
    public PhieuThue() {
    }

    public PhieuThue(int maPT, int maSan, int caThue, int tienSan, String maNT, Date ngayThue) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.caThue = caThue;
        this.tienSan = tienSan;
        this.maNT = maNT;
        this.ngayThue = ngayThue;
    }
}
