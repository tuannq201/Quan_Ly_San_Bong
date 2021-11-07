package com.example.myapplication.entity;

public class PhieuThue {
    public int maPT, maSan,tienSan;
    public String nguoiThue, caThue;
    public String ngayThue;
    public PhieuThue() {
    }

    public PhieuThue(int maPT, int maSan, String caThue, int tienSan, String nguoiThue, String ngayThue) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.caThue = caThue;
        this.tienSan = tienSan;
        this.nguoiThue = nguoiThue;
        this.ngayThue = ngayThue;
    }
}
