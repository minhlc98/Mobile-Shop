package com.example.nhocs.demonavigation.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.Model.ShrPreferences;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;
import com.example.nhocs.demonavigation.R;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Write_Review extends AppCompatActivity {

    ImageView img;
    TextView tvName, tvNhanXet;
    RatingBar ratingBar;
    ThongTinSanPham sp;
    Button btnGui;
    EditText edtTieuDe, edtNoiDung;
    Toolbar toolbar;
    String[] feel = {"", "Rất Không Hài Lòng", "Không Hài Lòng", "Bình Thường", "Hài Lòng", "Cực Kì Hài Lòng"};
    ThongTinNguoiDung tt = ShrPreferences.getInstance(this).getInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        Mapping();
        Event();
        getInfo();
    }

    public void Event() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int r = Math.round(rating);
                tvNhanXet.setText(feel[r]);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = Math.round(ratingBar.getRating());
                if (rating != 0) {
                    int id_pro = sp.getID();
                    int id_user = tt.getID();
                    String s = edtTieuDe.getText().toString();
                    String title = s.equals("") ? tvNhanXet.getText().toString() : s;
                    String content = edtNoiDung.getText().toString().trim();
                    final Dialog dialog = new Dialog(Write_Review.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_loading);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    RetrofitClient.getInstace().getService().insertReview(id_pro, id_user, rating, title, content)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<String>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(String res) {
                                    switch (res) {
                                        case "success":
                                            Toast.makeText(Write_Review.this, "Đánh giá của bạn đã được ghi nhận", Toast.LENGTH_LONG).show();
                                            finish();
                                            break;
                                        case "error":
                                            Toast.makeText(Write_Review.this, "Bạn đã đánh giá cho sản phẩm này rồi", Toast.LENGTH_LONG).show();
                                            break;
                                        default:
                                            Toast.makeText(Write_Review.this, res, Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.cancel();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(Write_Review.this, "Không có kết nối", Toast.LENGTH_LONG).show();
                                }
                            });
                    //                           .enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            try {
//                                String res = response.body().string();
//                                switch (res) {
//                                    case "success":
//                                        Toast.makeText(Write_Review.this, "Đánh giá của bạn đã được ghi nhận", Toast.LENGTH_LONG).show();
//                                        finish();
//                                        break;
//                                    case "error":
//                                        Toast.makeText(Write_Review.this, "Bạn đã đánh giá cho sản phẩm này rồi", Toast.LENGTH_LONG).show();
//                                        break;
//                                    default:
//                                        Toast.makeText(Write_Review.this, res, Toast.LENGTH_SHORT).show();
//                                }
//                                dialog.cancel();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                        }
//                    });
                } else {
                    Toast.makeText(Write_Review.this, "Bạn chưa đánh giá", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Mapping() {
        img = findViewById(R.id.img_WR);
        tvName = findViewById(R.id.tvName_WR);
        tvNhanXet = findViewById(R.id.tvNhanXetDanhGia);
        ratingBar = findViewById(R.id.rating_WR);
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        btnGui = findViewById(R.id.btnGui);
        toolbar = findViewById(R.id.toolbarVietNhanXet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

    }

    public void getInfo() {
        sp = (ThongTinSanPham) getIntent().getSerializableExtra("ThongTinSanPham");
        GlideApp.with(this).load(sp.getUrlHinh()).into(img);
        tvName.setText(sp.getTenSP());
    }
}
