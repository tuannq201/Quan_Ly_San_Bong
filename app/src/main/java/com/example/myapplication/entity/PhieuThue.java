package com.example.myapplication.entity;

import java.util.Date;

public class PhieuThue {
    public int maPT, maSan,tienSan;
    public String maNT, caThue;
    public String ngayThue;
    public PhieuThue() {
    }

    public PhieuThue(int maPT, int maSan, String caThue, int tienSan, String maNT, String ngayThue) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.caThue = caThue;
        this.tienSan = tienSan;
        this.maNT = maNT;
        this.ngayThue = ngayThue;
    }
}
