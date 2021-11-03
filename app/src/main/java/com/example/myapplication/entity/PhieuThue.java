package com.example.myapplication.entity;

import java.util.Date;

public class PhieuThue {
    public int maPT,maSan,caThue,tienSan;
    public String nguoiThue;
    public Date ngayThue;

    public PhieuThue() {
    }

    public PhieuThue(int maPT, int maSan, int maNT, int caThue, String nguoiThue, int tienSan, Date ngayThue) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.nguoiThue = nguoiThue;
        this.caThue = caThue;
        this.tienSan = tienSan;
        this.ngayThue = ngayThue;
    }
}
