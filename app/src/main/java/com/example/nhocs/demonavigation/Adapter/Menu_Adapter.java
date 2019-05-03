package com.example.nhocs.demonavigation.Adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.Model.LoaiSP;
import com.example.nhocs.demonavigation.R;
import java.util.ArrayList;



public class Menu_Adapter extends ArrayAdapter<LoaiSP> {
   Activity context;
   int layout_id;
   ArrayList<LoaiSP> arrayList;

    public Menu_Adapter( Activity context, int resource, ArrayList<LoaiSP> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout_id=resource;
        this.arrayList=objects;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout_id, null);
            viewHolder=new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(arrayList.get(position).getTenSP());
        if(position==0) GlideApp.with(context)
                                .load(arrayList.get(position).getHinhAnh())
                                .apply(RequestOptions.circleCropTransform())
                                .placeholder(R.drawable.no_image)
                                .into(viewHolder.image);
        else {
            GlideApp.with(context)
                    .load(arrayList.get(position).getHinhAnh())
                    .placeholder(R.drawable.no_image)
                    .into(viewHolder.image);
        }
        return convertView;
    }
    public class ViewHolder{
        public ImageView image;
        public TextView textView;
    }
}
