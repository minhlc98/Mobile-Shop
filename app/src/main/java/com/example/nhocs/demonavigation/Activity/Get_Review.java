package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhocs.demonavigation.Adapter.ReviewAdapter;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.Review;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;
import com.example.nhocs.demonavigation.R;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Get_Review extends AppCompatActivity {

    ListView listReview;
    ReviewAdapter adapter;
    ArrayList<Review> reviews;
    Toolbar toolbarNhanXet;
    ThongTinSanPham sp;
    Button btnWR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_review);
        Mapping();
        Event();
        getReviews(sp.getID());

    }

    public void Mapping() {
        listReview = findViewById(R.id.listReview);
        reviews = new ArrayList<>();
        adapter = new ReviewAdapter(reviews, this);
        listReview.setAdapter(adapter);
        toolbarNhanXet = findViewById(R.id.toolbarNhanXet);
        btnWR = findViewById(R.id.btnWriteReview);
        sp = (ThongTinSanPham) getIntent().getSerializableExtra("ThongTinSanPham");
        setSupportActionBar(toolbarNhanXet);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarNhanXet.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

    }

    public void getReviews(int id_pro) {
        RetrofitClient.getInstace().getService()
                .getReivew(id_pro)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<Review>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<Review> mReviews) {
                        if (mReviews.size() > 0) {
                            reviews.addAll(mReviews);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Get_Review.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
        //.enqueue(new Callback<ArrayList<Review>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Review>> call, Response<ArrayList<Review>> response) {
//                if(response.body().size()>0) {
//                    for (int i = 0; i < response.body().size(); ++i) {
//                        reviews.add(response.body().get(i));
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Review>> call, Throwable t) {
//                Toast.makeText(Get_Review.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void Event() {
        toolbarNhanXet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.isLogin) {
                    Intent intent = new Intent(Get_Review.this, Write_Review.class);
                    intent.putExtra("ThongTinSanPham", sp);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Get_Review.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
