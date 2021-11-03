package com.example.myapplication.entity;

import android.graphics.Bitmap;

public class User {
    public String taiKhoan, ten, matKhau, phanQuyen;
    public byte[] hinhAnh;

    public User() {
    }

    public User(String taiKhoan, String ten, String matKhau, String phanQuyen, byte[] hinhAnh) {
        this.taiKhoan = taiKhoan;
        this.ten = ten;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
        this.hinhAnh = hinhAnh;
    }
}
