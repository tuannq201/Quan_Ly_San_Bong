package com.example.myapplication.entity;

public class PhieuThue {
    public int maPT, maSan,tienSan, danhGia, sao, position;
    public String nguoiThue, caThue;
    public String ngayThue;
    public PhieuThue() {
    }

    public PhieuThue(int maPT, int maSan, int tienSan, int danhGia, int sao, String nguoiThue, String caThue, String ngayThue) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.tienSan = tienSan;
        this.danhGia = danhGia;
        this.sao = sao;
        this.nguoiThue = nguoiThue;
        this.caThue = caThue;
        this.ngayThue = ngayThue;
    }

    public PhieuThue(int maPT, int maSan, int tienSan, int danhGia, int sao, int position, String nguoiThue, String caThue, String ngayThue) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.tienSan = tienSan;
        this.danhGia = danhGia;
        this.sao = sao;
        this.position = position;
        this.nguoiThue = nguoiThue;
        this.caThue = caThue;
        this.ngayThue = ngayThue;
    }
}
