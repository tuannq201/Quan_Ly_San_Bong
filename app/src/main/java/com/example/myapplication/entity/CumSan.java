package com.example.myapplication.entity;

import java.io.Serializable;

public class CumSan implements Serializable {
    public int maCumSan, soDanhGia, soSao;
    public String tenCumSan,chuSan,diaChi;

    public CumSan(){

    }

    public CumSan(int maCumSan, String tenCumSan) {
        this.maCumSan = maCumSan;
        this.tenCumSan = tenCumSan;
    }
    public CumSan(int maCumSan, String tenCumSan, String chuSan, String diaChi) {
        this.maCumSan = maCumSan;
        this.tenCumSan = tenCumSan;
        this.chuSan = chuSan;
        this.diaChi = diaChi;
    }

    public CumSan(int maCumSan, int soDanhGia, int soSao, String tenCumSan, String chuSan, String diaChi) {
        this.maCumSan = maCumSan;
        this.soDanhGia = soDanhGia;
        this.soSao = soSao;
        this.tenCumSan = tenCumSan;
        this.chuSan = chuSan;
        this.diaChi = diaChi;
    }
}
