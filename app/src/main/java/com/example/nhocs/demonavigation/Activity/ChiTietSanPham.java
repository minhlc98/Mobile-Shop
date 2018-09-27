package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Model.SanPham;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    ImageView imageChiTiet;
    TextView txtTenSPChiTiet,txtGiaChiTiet, txtChiTiet;
    Button btnThemVaoGioHang;
    Spinner spinner;
    Toolbar toolbar;
    SanPham sp;
    TextView txt_count;
    ImageButton cart;
    MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Mapping();
        actionBar();
        getThongTin();
        EventSpinner();
        Event();
    }

    @Override
    protected void onResume() {
        if (txt_count!=null) txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
        super.onResume();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent1=new Intent(ChiTietSanPham.this,TimKiem.class);
        startActivity(intent1);
        return super.onOptionsItemSelected(item);
    }
    public void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        if (getIntent().getExtras().getBoolean("from_search")){
            menu.getItem(0).setVisible(false);
        }
        menuItem=menu.findItem(R.id.item_store);
        View view=menuItem.getActionView();
        if (view!=null) {
            txt_count=view.findViewById(R.id.txt_count);
            cart=view.findViewById(R.id.cart);
        }
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChiTietSanPham.this,GioHang.class);
                startActivity(intent);
            }
        });
        txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
        return super.onCreateOptionsMenu(menu);
    }
    public void EventSpinner() {
        Integer[] integers=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,integers);
        spinner.setAdapter(adapter);
    }
    public void getThongTin() {
        sp= (SanPham) getIntent().getSerializableExtra("ThongTinSanPham");
        Picasso.get().load(sp.getUrlHinh()).into(imageChiTiet);
        txtTenSPChiTiet.setText(sp.getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGiaChiTiet.setText("Giá "+decimalFormat.format(sp.getGia())+"đ");
        txtChiTiet.setText(sp.getMoTa());
    }
    public void Event(){
        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kiểm tra hàng đã có trong giỏ đồ chưa
                String sql=String.format("Select * from GIOHANG where id=%d",sp.getID());
                Cursor cursor= MainActivity.database.getData(sql);
                if (cursor.getCount()!=0)
                {
                    //Tồn tại thì cộng vào số lượng
                    cursor.moveToFirst();
                    int soluong=cursor.getInt(5);
                    soluong+=Integer.parseInt(spinner.getSelectedItem().toString());
                    String sql_Update=String.format("update GIOHANG set SOLUONG=%d where id=%d",soluong,sp.getID());
                    MainActivity.database.Query(sql_Update);
                    Toast.makeText(ChiTietSanPham.this,"Đã thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    //KHông tồn tại thì thêm mới vào
                    String sql_Insert=String.format("Insert into GIOHANG values(null,%d,'%s',%d,'%s',%d)",sp.getID(),sp.getTenSP(),sp.getGia(),sp.getUrlHinh(),Integer.parseInt(spinner.getSelectedItem().toString()));
                    MainActivity.database.Query(sql_Insert);
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    MainActivity.soluong_giohang++;
                    txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
                    Toast.makeText(ChiTietSanPham.this,"Đã thêm thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        image_favorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                image_favorite.setBackgroundResource(R.drawable.favorite1);
//                Toast.makeText(ChiTietSanPham.this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
//            }
//
//        });
    }
    public void Mapping() {
        toolbar=(Toolbar) findViewById(R.id.toolbarChiTiet);
        imageChiTiet=(ImageView) findViewById(R.id.imageChiTiet);
        txtTenSPChiTiet=(TextView) findViewById(R.id.txtTenSanPhamChiTiet);
        txtGiaChiTiet=(TextView) findViewById(R.id.txtGiaSPChiTiet);
        txtChiTiet=(TextView) findViewById(R.id.txtChiTietSP);
        spinner=(Spinner) findViewById(R.id.spinner);
        btnThemVaoGioHang=(Button) findViewById(R.id.btnThemVaoGioHang);
      //  image_favorite=(ImageButton) findViewById(R.id.image_favorite);
    }
}
