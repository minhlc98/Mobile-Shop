package com.example.nhocs.demonavigation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @SerializedName("ID")
    @Expose
    private int id;

    @SerializedName("TenSP")
    @Expose
    private String TenSP;

    @SerializedName("Hinh")
    @Expose
    private String HinhAnh;

    public LoaiSP(int id, String tenSP, String hinhAnh) {
        this.id = id;
        TenSP = tenSP;
        HinhAnh = hinhAnh;
    }
}
