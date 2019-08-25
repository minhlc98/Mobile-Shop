package com.example.nhocs.demonavigation.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhocs.demonavigation.Activity.GioHang;
import com.example.nhocs.demonavigation.Activity.MainActivity;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.Model.ThongTinGioHang;
import com.example.nhocs.demonavigation.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    private Activity context;

    public GioHangAdapter(Activity context, ArrayList<ThongTinGioHang> arrayList, int layout_id) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout_id = layout_id;
    }

    public static boolean check_if_has_change;
    private ArrayList<ThongTinGioHang> arrayList;
    private int layout_id;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout_id, null);
            viewHolder = new ViewHolder();
            viewHolder.imageViewGioHang = convertView.findViewById(R.id.imageViewGioHang);
            viewHolder.txtTenSPGioHang = convertView.findViewById(R.id.txtTenSPGioHang);
            viewHolder.txtGiaGioHang = convertView.findViewById(R.id.txtGiaGioHang);
            viewHolder.txtSoLuong = convertView.findViewById(R.id.txtSoLuongGioHang);
            viewHolder.btnTru = convertView.findViewById(R.id.btnTru);
            viewHolder.btnCong = convertView.findViewById(R.id.btnCong);
            viewHolder.delete = convertView.findViewById(R.id.delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtTenSPGioHang.setText(arrayList.get(position).getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaGioHang.setText("Giá " + decimalFormat.format(arrayList.get(position).getGia()) + " đ");
        viewHolder.txtSoLuong.setText(String.valueOf(arrayList.get(position).getSoLuong()));
        viewHolder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_has_change = true;
                GioHang.TongTien += arrayList.get(position).getGia();
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                GioHang.txtTongTien.setText(decimalFormat.format(GioHang.TongTien) + " đ");
                arrayList.get(position).setSoLuong(arrayList.get(position).getSoLuong() + 1);
                viewHolder.txtSoLuong.setText(String.valueOf(arrayList.get(position).getSoLuong()));
            }
        });
        viewHolder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList.get(position).getSoLuong() > 1) {
                    check_if_has_change = true;
                    GioHang.TongTien -= arrayList.get(position).getGia();
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    GioHang.txtTongTien.setText(decimalFormat.format(GioHang.TongTien) + " đ");
                    arrayList.get(position).setSoLuong(arrayList.get(position).getSoLuong() - 1);
                    viewHolder.txtSoLuong.setText(String.valueOf(arrayList.get(position).getSoLuong()));
                }
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete_Item(position);
            }
        });
        GlideApp.with(context).load(arrayList.get(position).getUrlHinh()).placeholder(R.drawable.no_image).into(viewHolder.imageViewGioHang);
        return convertView;
    }

    public void Delete_Item(final int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setCancelable(false);
        alert.setTitle("Thông báo");
        alert.setMessage("Bạn có muốn xóa " + arrayList.get(position).getTenSP() + " ra khỏi giỏ hàng không?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.database.Query("Delete from GIOHANG where id=" + arrayList.get(position).getID());
                GioHang.TongTien -= arrayList.get(position).getSoLuong() * arrayList.get(position).getGia();
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                GioHang.txtTongTien.setText(decimalFormat.format(GioHang.TongTien) + " đ");
                arrayList.remove(position);
                notifyDataSetChanged();
                dialog.cancel();
                MainActivity.soluong_giohang--;
                GioHang.toolbarGioHang.setTitle(String.format("Giỏ hàng (%d)", MainActivity.soluong_giohang));
                if (MainActivity.soluong_giohang == 0)
                    GioHang.txtNoiDung.setVisibility(View.VISIBLE);
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    class ViewHolder {
        ImageView imageViewGioHang;
        TextView txtTenSPGioHang, txtGiaGioHang, txtSoLuong;
        Button btnTru, btnCong;
        ImageView delete;
    }
}
