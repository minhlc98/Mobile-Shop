package com.example.nhocs.demonavigation.Model;

public class ThongTinGioHang {
    private int ID, Gia,SoLuong;

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public ThongTinGioHang(int ID, int gia, int soLuong, String tenSP, String urlHinh) {
        this.ID = ID;
        Gia = gia;
        SoLuong = soLuong;
        TenSP = tenSP;
        this.urlHinh = urlHinh;
    }

    private String TenSP;
    private String urlHinh;

    public int getID() {
        return ID;
    }

    public int getGia() {
        return Gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public String getTenSP() {
        return TenSP;
    }

    public String getUrlHinh() {
        return urlHinh;
    }



}
