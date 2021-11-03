package com.example.myapplication.entity;

import android.graphics.Bitmap;

public class User {
    private String taiKhoan, ten, matKhau, phanQuyen;
    private Bitmap hinhAnh;

    public User() {
    }

    public User(String taiKhoan, String ten, String matKhau, String phanQuyen, Bitmap hinhAnh) {
        this.taiKhoan = taiKhoan;
        this.ten = ten;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
        this.hinhAnh = hinhAnh;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(String phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public Bitmap getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Bitmap hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
