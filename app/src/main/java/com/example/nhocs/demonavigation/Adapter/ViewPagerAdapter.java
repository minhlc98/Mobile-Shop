package com.example.nhocs.demonavigation.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nhocs.demonavigation.Model.Banner;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<Banner> arrayList;

    public ViewPagerAdapter(ArrayList<Banner> arrayList, Activity context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    private Activity context;

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_banner, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        //Khi một request được bắt đầu, nó sẽ kiểm tra memory cache coi có dữ liệu hình ảnh không r tới kiểm tra disk cache
        //Ưu điểm: load hình ảnh nhanh vì không cần phải ra internet để load ảnh, mà load từ menory cache hoặc disk cache
        //Nhược điểm: Khi hình ảnh trên url bị thay đổi, lúc đó reuqest vẫn lấy menory cache hoặc disk cache mà không hề lấy từ url hình ảnh
        //Vì hình ảnh được tải về sẽ đc ghi vào memory cache và disk cache
        //.memoryPolicy(MemoryPolicy.NO_CACHE)
        GlideApp.with(context)
                .load(arrayList.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.no_image)
                .into(imageView);
        //Chỉ dùng memory cache để load ảnh nhanh hơn(cache của ram)
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.thegioididong.com/"));
                context.startActivity(intent);
            }
        });
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

}
