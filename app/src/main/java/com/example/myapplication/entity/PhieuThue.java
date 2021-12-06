package com.example.myapplication.entity;

public class PhieuThue {
    public int maPT, maSan,tienSan, danhGia, sao, position;
    public String nguoiThue, caThue;
    public String ngayThue, phanHoi;
    public int soKM;
    public PhieuThue() {
    }

    public PhieuThue(int maPT, int maSan, int tienSan, int danhGia, int sao, String nguoiThue, String caThue, String ngayThue, int soKM) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.tienSan = tienSan;
        this.danhGia = danhGia;
        this.sao = sao;
        this.nguoiThue = nguoiThue;
        this.caThue = caThue;
        this.ngayThue = ngayThue;
        this.soKM = soKM;
    }

    public PhieuThue(int maPT, int maSan, int tienSan, int danhGia, int sao, int position, String nguoiThue, String caThue, String ngayThue, int soKM) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.tienSan = tienSan;
        this.danhGia = danhGia;
        this.sao = sao;
        this.position = position;
        this.nguoiThue = nguoiThue;
        this.caThue = caThue;
        this.ngayThue = ngayThue;
        this.soKM = soKM;
    }

    public PhieuThue(int maPT, int maSan, int tienSan, int danhGia, int sao, int position, String nguoiThue, String caThue, String ngayThue, String phanHoi, int soKM) {
        this.maPT = maPT;
        this.maSan = maSan;
        this.tienSan = tienSan;
        this.danhGia = danhGia;
        this.sao = sao;
        this.position = position;
        this.nguoiThue = nguoiThue;
        this.caThue = caThue;
        this.ngayThue = ngayThue;
        this.phanHoi = phanHoi;
        this.soKM = soKM;
    }

    @Override
    public String toString() {
        return "PhieuThue{" +
                "maPT=" + maPT +
                ", maSan=" + maSan +
                ", tienSan=" + tienSan +
                ", danhGia=" + danhGia +
                ", sao=" + sao +
                ", position=" + position +
                ", nguoiThue='" + nguoiThue + '\'' +
                ", caThue='" + caThue + '\'' +
                ", ngayThue='" + ngayThue + '\'' +
                ", maKM='" +soKM + '\'' +
                '}';
    }
}
