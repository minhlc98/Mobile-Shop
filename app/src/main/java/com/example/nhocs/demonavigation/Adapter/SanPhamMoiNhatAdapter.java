package com.example.nhocs.demonavigation.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhocs.demonavigation.Activity.ChiTietSanPham;
import com.example.nhocs.demonavigation.R;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.nhocs.demonavigation.Model.SanPham;

public class SanPhamMoiNhatAdapter extends RecyclerView.Adapter<SanPhamMoiNhatAdapter.ItemHolder>  {
    Activity context;

    public SanPhamMoiNhatAdapter(Activity context, ArrayList<SanPham> arrayList, int layout_id) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout_id = layout_id;
    }

    ArrayList<SanPham> arrayList;
    int layout_id;
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(layout_id,null);
        ItemHolder itemHolder=new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.txtTenSP.setText(arrayList.get(position).getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtGia.setText("Giá "+decimalFormat.format(arrayList.get(position).getGia())+" đ");
        Picasso.get().load(arrayList.get(position).getUrlHinh()).placeholder(R.drawable.no_image).into(holder.hinhSP);
    }

    public void ClickItem(int position) {
        Intent intent=new Intent(context, ChiTietSanPham.class);
        intent.putExtra("ThongTinSanPham",arrayList.get(position));
        intent.putExtra("from_search",false);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ImageView hinhSP;
        TextView txtTenSP, txtGia;
        public ItemHolder(View itemView) {
            super(itemView);
            hinhSP=(ImageView) itemView.findViewById(R.id.hinhSP);
            txtTenSP=(TextView) itemView.findViewById(R.id.txtTenSP);
            txtGia=(TextView) itemView.findViewById(R.id.txtGia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickItem(getAdapterPosition());
                }
            });
        }
    }
}
