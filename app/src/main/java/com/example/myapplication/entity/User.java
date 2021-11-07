package com.example.myapplication.entity;

import android.graphics.Bitmap;

import java.util.Arrays;

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

    @Override
    public String toString() {
        return "User{" +
                "taiKhoan='" + taiKhoan + '\'' +
                ", ten='" + ten + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", phanQuyen='" + phanQuyen + '\'' +
                ", hinhAnh=" + Arrays.toString(hinhAnh) +
                '}';
    }
}
