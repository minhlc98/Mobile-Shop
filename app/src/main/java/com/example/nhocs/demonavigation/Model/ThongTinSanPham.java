package com.example.nhocs.demonavigation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThongTinSanPham extends SanPham {

    public String getMoTa() {
            return MoTa;
        }

    @SerializedName("MoTa")
    @Expose
    private String MoTa;

    public float getDanhGia() {
        return DanhGia;
    }

    @SerializedName("DanhGia")
    @Expose
    private float DanhGia;

    public int getSLDanhGia() {
        return SLDanhGia;
    }

    @SerializedName("SLDanhGia")
    @Expose
    private int SLDanhGia;
    public ThongTinSanPham(int ID, int gia, String tenSP, String urlHinh, String moTa, float danhGia, int slDanhGia) {
        super(ID,gia,tenSP,urlHinh);
        this.MoTa = moTa;
        this.DanhGia=danhGia;
        this.SLDanhGia=slDanhGia;
    }
}
