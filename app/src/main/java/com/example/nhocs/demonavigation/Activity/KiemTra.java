package com.example.nhocs.demonavigation.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Adapter.SP_DaMua_Adapter;
import com.example.nhocs.demonavigation.Ulti.Server;
import com.example.nhocs.demonavigation.Model.ThongTinKiemTra;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class KiemTra extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBar;
    TextView txtNoRes_InCheck,txtTong, txtLoi;
    EditText edtTenKH, edtIDDonHang;
    Button btnKiemTra;
    ListView lvDanhSachSP_DaMua;
    SP_DaMua_Adapter adapter;
    ArrayList<ThongTinKiemTra> danh_sach_sp_da_mua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra);
        Mapping();
        actionBar();
        Event();
    }
    public void Event(){
        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLoi.setVisibility(View.INVISIBLE);
                if (check_valid_info()) {
                    btnKiemTra.setEnabled(false);
                    txtTong.setText("0đ");
                    getData(formatURL(edtTenKH.getText().toString().trim(), edtIDDonHang.getText().toString()));//lấy sản phẩm khách hàng đã đặt từ database
                }
                else{
                    txtLoi.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public boolean check_valid_info(){
        String ID_DonHang=edtIDDonHang.getText().toString().trim();
        String TenKH=edtTenKH.getText().toString().trim();
        return ID_DonHang.matches("\\d+")&&TenKH.matches("[\\p{L}\\s]+");
    }
    public String formatURL(String TenKH, String ID) {
        TenKH=TenKH.replace(" ","%20");
        return String.format(Server.url_KiemTra+"?ID_DonHang=%s&TenKH=%s",ID,TenKH);
    }
    public void getData(String url) {
        danh_sach_sp_da_mua.clear();
        adapter.notifyDataSetChanged();
        txtNoRes_InCheck.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.length()!=0) {
                    int TongTien = 0;
                    for (int i = 0; i < response.length(); ++i) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ThongTinKiemTra thongTinKiemTra = new ThongTinKiemTra(
                                    jsonObject.getString("TenSP"),
                                    jsonObject.getInt("SoLuong"),
                                    jsonObject.getInt("Gia"));
                            TongTien += thongTinKiemTra.getGia()*thongTinKiemTra.getSL();
                            danh_sach_sp_da_mua.add(thongTinKiemTra);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    adapter.notifyDataSetChanged();
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    txtTong.setText(decimalFormat.format(TongTien).toString());

                }
                else{
                    txtNoRes_InCheck.setVisibility(View.VISIBLE);
                }
                btnKiemTra.setEnabled(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
                btnKiemTra.setEnabled(true);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void Mapping() {
        toolbar=(Toolbar) findViewById(R.id.toolbarKiemTra);
        progressBar=(ProgressBar) findViewById(R.id.progress_bar);
        txtNoRes_InCheck=(TextView) findViewById(R.id.txtNoRes_InCheck);
        txtLoi=(TextView) findViewById(R.id.txtLoi);
        edtIDDonHang=(EditText) findViewById(R.id.edtIDDonHang);
        edtTenKH=(EditText) findViewById(R.id.edtTenKH);
        btnKiemTra=(Button) findViewById(R.id.btnKiemTra);
        danh_sach_sp_da_mua=new ArrayList<>();
        lvDanhSachSP_DaMua=(ListView) findViewById(R.id.lvDanhSachSP);
        adapter=new SP_DaMua_Adapter(this,R.layout.san_pham_da_mua,danh_sach_sp_da_mua);
        lvDanhSachSP_DaMua.setAdapter(adapter);
        txtTong=(TextView) findViewById(R.id.txtTong);
    }
    public void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
