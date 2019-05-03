package com.example.nhocs.demonavigation.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;
import com.example.nhocs.demonavigation.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class SanPhamAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<ThongTinSanPham> arrayList;
    int layout_id;
    public SanPhamAdapter(Activity context, int resource, ArrayList<ThongTinSanPham> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout_id=resource;
        this.arrayList=objects;
    }
    public class ViewHolder{
        ImageView imageSanPham;
        TextView textViewTenSP, textViewGia, textViewMoTa;
        RatingBar rating;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout_id, null);
            viewHolder=new ViewHolder();
            viewHolder.imageSanPham=(ImageView) convertView.findViewById(R.id.imageSanPham);
            viewHolder.textViewTenSP=(TextView) convertView.findViewById(R.id.textViewTenSP);
            viewHolder.textViewGia=(TextView) convertView.findViewById(R.id.textViewGia);
            viewHolder.textViewMoTa=(TextView) convertView.findViewById(R.id.textViewMoTa);
            viewHolder.rating=(RatingBar) convertView.findViewById(R.id.rating_product);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.textViewGia.setText("Giá "+decimalFormat.format(arrayList.get(position).getGia())+"đ");
        viewHolder.textViewTenSP.setText(arrayList.get(position).getTenSP());
        viewHolder.textViewMoTa.setText(arrayList.get(position).getMoTa());
        viewHolder.rating.setRating(arrayList.get(position).getDanhGia());
        GlideApp.with(context).load(arrayList.get(position).getUrlHinh()).placeholder(R.drawable.no_image).into(viewHolder.imageSanPham);
        return convertView;
    }
}
