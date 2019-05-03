package com.example.nhocs.demonavigation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SanPham implements Serializable {
    @SerializedName("ID")
    @Expose
    protected int ID;

    @SerializedName("Gia")
    @Expose
    protected int Gia;

    @SerializedName("TenSP")
    @Expose
    protected String TenSP;

    @SerializedName("Hinh")
    @Expose
    protected String urlHinh;

    public SanPham(int ID, int gia, String tenSP, String urlHinh) {
        this.ID = ID;
        this.Gia = gia;
        this.TenSP = tenSP;
        this.urlHinh = urlHinh;
    }
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
        return urlHinh;
    }

}
