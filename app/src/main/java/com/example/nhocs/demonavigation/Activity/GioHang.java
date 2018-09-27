package com.example.nhocs.demonavigation.Activity;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.nhocs.demonavigation.Adapter.GioHangAdapter;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Model.ThongTinGioHang;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHang extends AppCompatActivity {

    public static TextView txtNoiDung;
    public static TextView txtTongTien;
    Button btnThanhToan, btnTiepTuc;
    public static Toolbar toolbarGioHang;
    public static int TongTien;
    static ArrayList<ThongTinGioHang> mang_gio_hang;
    ListView recyclerViewGioHang;
    GioHangAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Mapping();
        actionBar();
        Event();
    }

    @Override
    protected void onResume() {
        getGioHang();
        toolbarGioHang.setTitle(String.format("Giỏ hàng (%d)",MainActivity.soluong_giohang));
        super.onResume();
    }
    public void Event() {
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GioHang.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mang_gio_hang.size()>0) {
                    Intent intent = new Intent(GioHang.this, ThanhToan.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    protected void onPause() {
        if (GioHangAdapter.check_if_has_change) {
            for (int i = 0; i < mang_gio_hang.size(); ++i) {
                String sql = String.format("Update GIOHANG set SOLUONG=%d where ID=%d",
                        mang_gio_hang.get(i).getSoLuong(),
                        mang_gio_hang.get(i).getID());
                MainActivity.database.Query(sql);
            }
        }
        super.onPause();
    }
    @Override
    protected void onStop() {
        GioHangAdapter.check_if_has_change=false;
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        //release: giải phóng biến static
        mang_gio_hang=null;
        toolbarGioHang=null;
        txtNoiDung=null;
        txtTongTien=null;
        super.onDestroy();
    }
    public void actionBar() {
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title=String.format("Giỏ hàng (%d)",MainActivity.soluong_giohang);
        toolbarGioHang.setTitle(title);
        toolbarGioHang.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void Mapping() {
        txtNoiDung=(TextView) findViewById(R.id.txtNoiDung);
        toolbarGioHang=(Toolbar) findViewById(R.id.toolbarGioHang);
        recyclerViewGioHang=(ListView) findViewById(R.id.recyclerViewGioHang);
        txtTongTien=(TextView) findViewById(R.id.txtTongTien);
        btnThanhToan=(Button) findViewById(R.id.btnThanhToan);
        btnTiepTuc=(Button) findViewById(R.id.btnTiepTuc);
        mang_gio_hang=new ArrayList<>();
        adapter=new GioHangAdapter(this,mang_gio_hang,R.layout.layout_gio_hang);
        recyclerViewGioHang.setAdapter(adapter);
    }
    public void getGioHang() {
        TongTien=0;
        mang_gio_hang.clear();
        Cursor cursor=MainActivity.database.getData("Select * from GIOHANG");
        if (cursor.getCount()!=0)
        {
            txtNoiDung.setVisibility(View.INVISIBLE);
            while(cursor.moveToNext())
            {
                ThongTinGioHang tt=new ThongTinGioHang(
                        cursor.getInt(1),
                        cursor.getInt(3),
                        cursor.getInt(5),
                        cursor.getString(2),
                        cursor.getString(4));
                TongTien+=tt.getGia()*tt.getSoLuong();
                mang_gio_hang.add(tt);
            }

        }
        else{
            txtNoiDung.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(TongTien)+" đ");
    }
}
