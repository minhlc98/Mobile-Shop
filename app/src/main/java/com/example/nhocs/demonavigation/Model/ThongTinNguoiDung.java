package com.example.nhocs.demonavigation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThongTinNguoiDung implements Serializable {

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("id")
    @Expose
    private int ID;

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("acc")
    @Expose
    private String acc;

    @SerializedName("pass")
    @Expose
    private String pass;

    @SerializedName("sex")
    @Expose
    private boolean sex;

    @SerializedName("img")
    @Expose
    private String image;

    @SerializedName("countryCode")
    @Expose
    private int ID_countryCode;

    @SerializedName("phone")
    @Expose
    private String phoneNumber;

    @SerializedName("addr")
    @Expose
    private String address;

    @SerializedName("email")
    @Expose
    private String email;


    public ThongTinNguoiDung(int ID, String acc, String pass, boolean sex, String image, int ID_countryCode, String fullName, String phoneNumber, String address, String email) {
        this.ID = ID;
        this.acc = acc;
        this.pass = pass;
        this.sex = sex;
        this.image = image;
        this.ID_countryCode = ID_countryCode;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }


    public int getID_countryCode() {
        return ID_countryCode;
    }

    public void setID_countryCode(int ID_countryCode) {
        this.ID_countryCode = ID_countryCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ThongTinNguoiDung(String fullName,int id, String phoneNumber, String address, String email) {
        this.fullName = fullName;
        this.ID_countryCode=id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
}
