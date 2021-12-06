package com.example.myapplication.entity;

public class KhuyenMai {
    public int maID, maSan;
    public int soKM;
    public String ca, ngay;

    public KhuyenMai() {
    }

    public KhuyenMai(int maID, int soKM, int maSan, String ca, String ngay) {
        this.maID = maID;
        this.soKM = soKM;
        this.maSan = maSan;
        this.ca = ca;
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "KhuyenMai{" +
                "maID=" + maID +
                ", maSan=" + maSan +
                ", soKM=" + soKM +
                ", ca='" + ca + '\'' +
                ", ngay='" + ngay + '\'' +
                '}';
    }
}
