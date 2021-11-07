package com.example.myapplication.entity;

public class CaSan {
    public int khuyenMai;
    public String ngayThue,caThue;
    public String trangThai;

    public CaSan() {
    }

    public CaSan(String caThue, String ngayThue, String trangThai, int khuyenMai) {
        this.caThue = caThue;
        this.ngayThue = ngayThue;
        this.trangThai = trangThai;
        this.khuyenMai = khuyenMai;
    }
}

