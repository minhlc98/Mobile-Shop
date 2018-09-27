package com.example.nhocs.demonavigation.Adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nhocs.demonavigation.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<String> arrayList;

    public ViewPagerAdapter(ArrayList<String> arrayList, Activity context) {
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
        Picasso.get().load(arrayList.get(position)).placeholder(R.drawable.no_image).into(imageView);
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
