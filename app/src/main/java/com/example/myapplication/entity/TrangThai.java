package com.example.myapplication.entity;

public class TrangThai {
    public int maSan,tienSan, color;
    public String taiKhoan, ca, ngay;
    public int maPT;
    public int soKM;

    public TrangThai() {
    }

    public TrangThai(int maSan, int tienSan, int color, String taiKhoan, String ca, String ngay, int soKM) {
        this.maSan = maSan;
        this.tienSan = tienSan;
        this.color = color;
        this.taiKhoan = taiKhoan;
        this.ca = ca;
        this.ngay = ngay;
        this.soKM = soKM;
    }

    @Override
    public String toString() {
        return "TrangThai{" +
                "maSan=" + maSan +
                ", tienSan=" + tienSan +
                ", color=" + color +
                ", taiKhoan='" + taiKhoan + '\'' +
                ", ca='" + ca + '\'' +
                ", ngay='" + ngay + '\'' +
                ", maPT=" + maPT +
                ", soKM=" + soKM +
                '}';
    }
}
