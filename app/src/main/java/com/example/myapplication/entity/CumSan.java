package com.example.myapplication.entity;

import java.io.Serializable;

public class CumSan implements Serializable {
    public int maCumSan;
    public String tenCumSan,chuSan,diaChi;

    public CumSan(){

    }

    public CumSan(int maCumSan, String tenCumSan, String chuSan, String diaChi) {
        this.maCumSan = maCumSan;
        this.tenCumSan = tenCumSan;
        this.chuSan = chuSan;
        this.diaChi = diaChi;
    }
}
