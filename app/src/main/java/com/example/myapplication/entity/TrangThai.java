package com.example.myapplication.entity;

public class TrangThai {
    public int maSan,tienSan, color;
    public String taiKhoan, ca, ngay;
    public int maPT;

    public TrangThai() {
    }

    public TrangThai(int maSan, int tienSan, int color, String taiKhoan, String ca, String ngay) {
        this.maSan = maSan;
        this.tienSan = tienSan;
        this.color = color;
        this.taiKhoan = taiKhoan;
        this.ca = ca;
        this.ngay = ngay;
    }
}
