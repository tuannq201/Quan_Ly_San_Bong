package com.example.myapplication.entity;

public class TrangThai {
    public int maSan,tienSan;
    public String taiKhoan, ca, ngay;

    public TrangThai() {
    }

    public TrangThai(int maSan,int tienSan, String taiKhoan, String ca, String ngay) {
        this.maSan = maSan;
        this.tienSan = tienSan;
        this.taiKhoan = taiKhoan;
        this.ca = ca;
        this.ngay = ngay;
    }

}
