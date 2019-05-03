package com.example.nhocs.demonavigation.Model;

public class ThongTinGioHang extends SanPham {
    private int SoLuong;

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public ThongTinGioHang(int ID, int gia, int soLuong, String tenSP, String urlHinh) {
        super(ID,gia,tenSP,urlHinh);
        this.SoLuong=soLuong;
    }
    public int getSoLuong() {
        return SoLuong;
    }
}
