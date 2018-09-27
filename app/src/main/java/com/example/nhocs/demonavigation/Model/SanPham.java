package com.example.nhocs.demonavigation.Model;

import java.io.Serializable;

public class SanPham  implements Serializable {
    private int ID, Gia ;
    private String TenSP;

    public int getID() {
        return ID;
    }

    public int getGia() {
        return Gia;
    }

    public String getTenSP() {
        return TenSP;
    }

    public String getUrlHinh() {
        return UrlHinh;
    }

    public String getMoTa() {
        return MoTa;
    }

    private String UrlHinh;
    private String MoTa;

    public SanPham(int ID, int gia, String tenSP, String urlHinh, String moTa) {
        this.ID = ID;
        Gia = gia;
        TenSP = tenSP;
        UrlHinh = urlHinh;
        MoTa = moTa;
    }


}
