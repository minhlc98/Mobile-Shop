package com.example.nhocs.demonavigation.Model;

public class LoaiSP {
    public int getId() {
        return id;
    }

    public String getTenSP() {
        return TenSP;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    private int id;
    private String TenSP, HinhAnh;

    public LoaiSP(int id, String tenSP, String hinhAnh) {
        this.id = id;
        TenSP = tenSP;
        HinhAnh = hinhAnh;
    }
}
