package com.example.myapplication.entity;

public class DanhGia {
    public String taiKhoanNT, tenNT, danhGia, ngayThue, tenSan;
    public int sao, giaThue;

    public DanhGia() {
    }

    public DanhGia(String taiKhoanNT, String tenNT, String danhGia, String ngayThue, String tenSan, int sao, int giaThue) {
        this.taiKhoanNT = taiKhoanNT;
        this.tenNT = tenNT;
        this.danhGia = danhGia;
        this.ngayThue = ngayThue;
        this.tenSan = tenSan;
        this.sao = sao;
        this.giaThue = giaThue;
    }
}
