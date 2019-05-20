package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.request.RequestOptions;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.Model.Review;
import com.example.nhocs.demonavigation.Model.Statistic_Rating;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;
import com.example.nhocs.demonavigation.R;
import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPham extends AppCompatActivity {
    ImageView imageChiTiet, img_user[];
    TextView txtTenSPChiTiet,txtGiaChiTiet, txtChiTiet, tv[], feedbackTitle[], feedbackAt[], userName[], feedbackContent[];
    Button btnThemVaoGioHang, btnVietNhanXet, btnXemTatCa;
    Toolbar toolbar;
    ThongTinSanPham sp;
    TextView txt_count, slDanhGia, slDanhGia_cus, tvRating;
    ImageButton cart;
    MenuItem menuItem;
    RatingBar rating_detail, rating_cus, feedbackRating[];
    ToggleButton btnSeeMore;
    ProgressBar progressBar[];
    RelativeLayout layoutFB_u1, layoutFB_u2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        Mapping();
        actionBar();
        getThongTin();
        Event();
        getStatistic();
    }

    @Override
    protected void onResume() {
        if (txt_count!=null) txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
        getFeedback();
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
    public void getThongTin() {
        sp= (ThongTinSanPham) getIntent().getSerializableExtra("ThongTinSanPham");
        GlideApp.with(this).load(sp.getUrlHinh()).into(imageChiTiet);
        txtTenSPChiTiet.setText(sp.getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGiaChiTiet.setText("Giá "+decimalFormat.format(sp.getGia())+"đ");
        txtChiTiet.setText(sp.getMoTa().substring(0,50)+"...");
        rating_detail.setRating(sp.getDanhGia());
        slDanhGia.setText(String.valueOf(sp.getSLDanhGia())+" Đánh giá");
        rating_cus.setRating(sp.getDanhGia());
        tvRating.setText(String.valueOf(sp.getDanhGia())+"/5");
        slDanhGia_cus.setText(String.valueOf(sp.getSLDanhGia())+" Đánh Giá");
        if (sp.getDanhGia()==0){
            layoutFB_u1.setVisibility(View.GONE);
            layoutFB_u2.setVisibility(View.GONE);
        }
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
                    soluong++;
                    String sql_Update=String.format("update GIOHANG set SOLUONG=%d where id=%d",soluong,sp.getID());
                    MainActivity.database.Query(sql_Update);
                    Toast.makeText(ChiTietSanPham.this,"Đã thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    //KHông tồn tại thì thêm mới vào
                    String sql_Insert=String.format("Insert into GIOHANG values(null,%d,'%s',%d,'%s',%d)",sp.getID(),sp.getTenSP(),sp.getGia(),sp.getUrlHinh(),1);
                    MainActivity.database.Query(sql_Insert);
                    MainActivity.soluong_giohang++;
                    txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
                    Toast.makeText(ChiTietSanPham.this,"Đã thêm thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVietNhanXet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.isLogin) {
                    Intent intent = new Intent(ChiTietSanPham.this, Write_Review.class);
                    intent.putExtra("ThongTinSanPham", sp);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(ChiTietSanPham.this, LoginActivity.class);
                    startActivity(intent
                    );
                }
            }
        });
        btnXemTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPham.this,Get_Review.class);
                intent.putExtra("ThongTinSanPham",sp);
                startActivity(intent);
            }
        });
        btnSeeMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) txtChiTiet.setText(sp.getMoTa());
                else txtChiTiet.setText(sp.getMoTa().substring(0,50)+"...");
            }
        });
    }
    public void Mapping() {
        toolbar=(Toolbar) findViewById(R.id.toolbarChiTiet);
        imageChiTiet=(ImageView) findViewById(R.id.imageChiTiet);
        txtTenSPChiTiet=(TextView) findViewById(R.id.txtTenSanPhamChiTiet);
        txtGiaChiTiet=(TextView) findViewById(R.id.txtGiaSPChiTiet);
        txtChiTiet=(TextView) findViewById(R.id.txtChiTietSP);
        //spinner=(Spinner) findViewById(R.id.spinner);
        btnThemVaoGioHang=(Button) findViewById(R.id.btnThemVaoGioHang);
        btnVietNhanXet=findViewById(R.id.btnVietNhanXet);
        btnXemTatCa=findViewById(R.id.btnXemTatCa);
        rating_detail=findViewById(R.id.rating_detail);
        rating_cus=findViewById(R.id.rating_customer);
        tvRating=findViewById(R.id.tvRating);
        slDanhGia=findViewById(R.id.slDanhGia);
        slDanhGia_cus=findViewById(R.id.slDanhGia_cus);
        btnSeeMore=findViewById(R.id.btnSeeMore);
        layoutFB_u1=findViewById(R.id.feedBack_u1);
        layoutFB_u2=findViewById(R.id.feedBack_u2);

        progressBar=new ProgressBar[5];
        progressBar[0]=findViewById(R.id.progress1);
        progressBar[1]=findViewById(R.id.progress2);
        progressBar[2]=findViewById(R.id.progress3);
        progressBar[3]=findViewById(R.id.progress4);
        progressBar[4]=findViewById(R.id.progress5);

        tv=new TextView[5];
        tv[0]=findViewById(R.id.tv1);
        tv[1]=findViewById(R.id.tv2);
        tv[2]=findViewById(R.id.tv3);
        tv[3]=findViewById(R.id.tv4);
        tv[4]=findViewById(R.id.tv5);

        feedbackTitle=new TextView[2];
        feedbackAt=new TextView[2];
        feedbackContent=new TextView[2];
        feedbackRating=new RatingBar[2];
        userName=new TextView[2];
        img_user=new ImageView[2];

        feedbackTitle[0]=findViewById(R.id.title_1);
        feedbackTitle[1]=findViewById(R.id.title_2);
        feedbackAt[0]=findViewById(R.id.date_1);
        feedbackAt[1]=findViewById(R.id.date_2);
        feedbackRating[0]=findViewById(R.id.rate_1);
        feedbackRating[1]=findViewById(R.id.rate_2);
        feedbackContent[0]=findViewById(R.id.content_1);
        feedbackContent[1]=findViewById(R.id.content_2);
        userName[0]=findViewById(R.id.name_1);
        userName[1]=findViewById(R.id.name_2);
        img_user[0]=findViewById(R.id.img_1);
        img_user[1]=findViewById(R.id.img_2);


      //  image_favorite=(ImageButton) findViewById(R.id.image_favorite);
    }
    public void getStatistic(){
        RetrofitClient.getInstace().getService().getStatistic(sp.getID()).enqueue(new Callback<ArrayList<Statistic_Rating>>() {
            @Override
            public void onResponse(Call<ArrayList<Statistic_Rating>> call, Response<ArrayList<Statistic_Rating>> response) {
                int total=_total(response.body());
                for (int i=0;i<response.body().size();++i){
                    int n = Math.round(((float)response.body().get(i).getStatistic() / total) * 100);
                    progressBar[i].setProgress(n);
                    tv[i].setText(n+"%");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Statistic_Rating>> call, Throwable t) {
                Toast.makeText(ChiTietSanPham.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }// Thống kê đánh giá
    public int _total(ArrayList<Statistic_Rating> arr){
        int total=0;
        for (int i=0;i<arr.size();++i){
            total+=arr.get(i).getStatistic();
        }
        Log.d("total", String.valueOf(total));
        return total;
    }
    public void getFeedback(){
        RetrofitClient.getInstace().getService().getReivew(sp.getID()).enqueue(new Callback<ArrayList<Review>>() {
            @Override
            public void onResponse(Call<ArrayList<Review>> call, Response<ArrayList<Review>> response) {
                int size=response.body().size();
                if (size > 0) {
                    switch(size){

                        //Nếu có 1 người đánh giá thì hiển thị 1 cái và k có nút xem tất cả
                        //Nếu có 2 người đánh giá thì hiển thị 2 cái và k có nút xem tất cả
                        //Nếu có hơn 2 người đánh giá thì hiển thị 2 cái và có nút xem tất cả

                        case 1:layoutFB_u1.setVisibility(View.VISIBLE);break;

                        case 2:layoutFB_u2.setVisibility(View.VISIBLE);btnXemTatCa.setVisibility(View.GONE);break;

                        default:layoutFB_u1.setVisibility(View.VISIBLE);
                                layoutFB_u2.setVisibility(View.VISIBLE);
                                size = 2;
                                btnXemTatCa.setVisibility(View.VISIBLE);
                    }
                    for (int i = 0; i < size; ++i) {
                        Review review = response.body().get(i);
                        feedbackTitle[i].setText(review.getTitle());
                        feedbackAt[i].setText(review.getDate());
                        feedbackContent[i].setText(review.getContent());
                        userName[i].setText(review.getName());
                        feedbackRating[i].setRating(review.getRating());
                        String url = "http://minhlc.000webhostapp.com/" + review.getImage();
                        GlideApp.with(ChiTietSanPham.this)
                                .load(url)
                                .apply(RequestOptions.circleCropTransform())
                                .into(img_user[i]);
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Review>> call, Throwable t) {

            }
        });
    }
}
