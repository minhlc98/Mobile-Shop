package com.example.nhocs.demonavigation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThongTinKiemTra {
    @SerializedName("TenSP")
    @Expose
    private String TenSP;
    @SerializedName("SoLuong")
    @Expose
    private int SL;

    public ThongTinKiemTra(String TenSP, int SL, int Gia) {
        this.TenSP=TenSP;
        this.SL=SL;
        this.Gia=Gia;
    }
    public String getTenSP() {
        return TenSP;
    }

    public int getSL() {
        return SL;
    }

    public int getGia() {
        return Gia;
    }

    @SerializedName("Gia")
    @Expose
    private int Gia;
}
