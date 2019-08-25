package com.example.nhocs.demonavigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.Model.Review;
import com.example.nhocs.demonavigation.R;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    public ReviewAdapter(ArrayList<Review> list, Context context) {
        this.list = list;
        this.context = context;
    }

    private ArrayList<Review> list;
    private Context context;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.review_row, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.tvTitle=convertView.findViewById(R.id.tvTitle);
            viewHolder.tvContent=convertView.findViewById(R.id.tvContent);
            viewHolder.tvName=convertView.findViewById(R.id.tvName);
            viewHolder.tvDate=convertView.findViewById(R.id.tvDate);
            viewHolder.rating_star=convertView.findViewById(R.id.rating_star);
            viewHolder.img_user=convertView.findViewById(R.id.img_user);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(list.get(position).getTitle());
        viewHolder.tvDate.setText(list.get(position).getDate());
        viewHolder.tvName.setText(list.get(position).getName());
        viewHolder.tvContent.setText(list.get(position).getContent());
        viewHolder.rating_star.setRating(list.get(position).getRating());
        String urlImage="http://minhlc.000webhostapp.com/"+list.get(position).getImage();
        GlideApp.with(context)
                .load(urlImage)
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.img_user);
        return convertView;
    }
    class ViewHolder{
        TextView tvTitle, tvDate, tvContent, tvName;
        RatingBar rating_star;
        ImageView img_user;
    }
}
