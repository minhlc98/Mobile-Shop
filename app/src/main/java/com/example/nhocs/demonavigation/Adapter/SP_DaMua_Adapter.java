package com.example.nhocs.demonavigation.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.nhocs.demonavigation.Model.ThongTinKiemTra;
import com.example.nhocs.demonavigation.R;

public class SP_DaMua_Adapter extends BaseAdapter{
    Activity context;
    int layout_id;

    public SP_DaMua_Adapter(Activity context, int layout_id, ArrayList<ThongTinKiemTra> arrayList) {
        this.context = context;
        this.layout_id = layout_id;
        this.arrayList = arrayList;
    }

    ArrayList<ThongTinKiemTra> arrayList;


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            LayoutInflater inflater=context.getLayoutInflater();
            convertView=inflater.inflate(layout_id,null);
            viewHolder=new ViewHolder();
            viewHolder.txtSTT=(TextView) convertView.findViewById(R.id.txtSTT);
            viewHolder.txtTenSP_KiemTra=(TextView) convertView.findViewById(R.id.txtTenSP_KiemTra);
            viewHolder.txtSL=(TextView) convertView.findViewById(R.id.txtSL);
            viewHolder.txtGiaSP=(TextView) convertView.findViewById(R.id.txtGiaSP);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.txtSTT.setText(String.valueOf(position+1));
        viewHolder.txtTenSP_KiemTra.setText(arrayList.get(position).getTenSP());
        viewHolder.txtSL.setText(String.valueOf(arrayList.get(position).getSL()));
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtGiaSP.setText(decimalFormat.format(arrayList.get(position).getGia()).toString());
        return convertView;
    }
    class ViewHolder{
        TextView txtSTT, txtTenSP_KiemTra, txtSL, txtGiaSP;
    }
}