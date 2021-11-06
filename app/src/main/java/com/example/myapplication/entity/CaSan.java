package com.example.myapplication.entity;

public class CaSan {
    public int caThue,khuyenMai;
    public String ngayThue;
    public String trangThai;

    public CaSan() {
    }

    public CaSan(int caThue, String ngayThue, String trangThai, int khuyenMai) {
        this.caThue = caThue;
        this.ngayThue = ngayThue;
        this.trangThai = trangThai;
        this.khuyenMai = khuyenMai;
    }
}

