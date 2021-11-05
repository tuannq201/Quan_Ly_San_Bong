package com.example.myapplication.entity;

public class TrangThai {
    public int maSan;
    public String taiKhoan, ca, ngay;

    public TrangThai() {
    }

    public TrangThai(int maSan, String taiKhoan, String ca, String ngay) {
        this.maSan = maSan;
        this.taiKhoan = taiKhoan;
        this.ca = ca;
        this.ngay = ngay;
    }

}
