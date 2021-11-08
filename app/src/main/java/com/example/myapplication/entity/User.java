package com.example.myapplication.entity;

import android.graphics.Bitmap;

import java.util.Arrays;

public class User {
    public String taiKhoan, hoTen, tenCumSan, matKhau, phanQuyen;
    public byte[] hinhAnh;

    public User() {
    }

    public User(String taiKhoan, String hoTen, String tenCumSan, String matKhau, String phanQuyen, byte[] hinhAnh) {
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.tenCumSan = tenCumSan;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "User{" +
                "taiKhoan='" + taiKhoan + '\'' +
                ", hoten='" + hoTen + '\'' +
                ", tencumsan='" + tenCumSan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", phanQuyen='" + phanQuyen + '\'' +
                ", hinhAnh=" + Arrays.toString(hinhAnh) +
                '}';
    }
}
