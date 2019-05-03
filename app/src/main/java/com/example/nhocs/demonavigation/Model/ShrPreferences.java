package com.example.nhocs.demonavigation.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class ShrPreferences {
    public SharedPreferences shp;
    public SharedPreferences.Editor editor;
    public static ShrPreferences mInstance;
    public final static String shrName="data";
    public final  static String key="info";
    public Gson gson;

    public ShrPreferences(Context context){
        shp=context.getSharedPreferences(shrName, Context.MODE_PRIVATE);
        editor=shp.edit();
        gson=new Gson();
    }
    public static ShrPreferences getInstance(Context context){
        if (mInstance == null){
           mInstance=new ShrPreferences(context);
        }
        return mInstance;
    }
    public ThongTinNguoiDung getInfo(){
        String json=shp.getString(key, "");
        if (! json.equals("")) return gson.fromJson(json, ThongTinNguoiDung.class);
        return null;
    }
    public void putInfo(ThongTinNguoiDung tt){

        String json=gson.toJson(tt);
        if (tt==null) json="";
        editor.putString(key, json);
        editor.apply();
    }


}
