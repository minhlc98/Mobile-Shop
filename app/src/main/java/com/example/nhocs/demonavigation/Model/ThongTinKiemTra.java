package com.example.nhocs.demonavigation.Model;

public class ThongTinKiemTra {
    private String TenSP;
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

    private int Gia;
}
