package com.example.nhocs.demonavigation.Adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nhocs.demonavigation.Model.Banner;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.R;
import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<Banner> arrayList;

    public ViewPagerAdapter(ArrayList<Banner> arrayList, Activity context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    Activity context;

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.custom_banner,null);
        ImageView imageView=(ImageView) view.findViewById(R.id.image);
        //Khi một request được bắt đầu, nó sẽ kiểm tra memory cache coi có dữ liệu hình ảnh không r tới kiểm tra disk cache
        //Ưu điểm: load hình ảnh nhanh vì không cần phải ra internet để load ảnh, mà load từ menory cache hoặc disk cache
        //Nhược điểm: Khi hình ảnh trên url bị thay đổi, lúc đó reuqest vẫn lấy menory cache hoặc disk cache mà không hề lấy từ url hình ảnh
        //Vì hình ảnh được tải về sẽ đc ghi vào memory cache và disk cache
        //.memoryPolicy(MemoryPolicy.NO_CACHE)
        GlideApp.with(context).load(arrayList.get(position).getUrl()).placeholder(R.drawable.no_image).into(imageView);
        //Chỉ dùng memory cache để load ảnh nhanh hơn(cache của ram)

        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager=(ViewPager) container;
        View view=(View) object;
        viewPager.removeView(view);
    }

}
