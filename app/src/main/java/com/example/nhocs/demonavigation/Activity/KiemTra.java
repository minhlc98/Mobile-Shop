package com.example.nhocs.demonavigation.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.nhocs.demonavigation.Adapter.SP_DaMua_Adapter;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ThongTinKiemTra;
import com.example.nhocs.demonavigation.R;
import java.text.DecimalFormat;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KiemTra extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
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
                String ID_DonHang=edtIDDonHang.getText().toString();
                String TenKH=edtTenKH.getText().toString().trim();
                if (check_valid_info(ID_DonHang,TenKH)) {
                    btnKiemTra.setEnabled(false);
                    txtTong.setText("0đ");
                    getData(TenKH, ID_DonHang);//lấy sản phẩm khách hàng đã đặt từ database
                }
                else{
                    txtLoi.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public boolean check_valid_info(String ID_DonHang,String TenKH){
        return ID_DonHang.matches("\\d+")&&TenKH.matches("[\\p{L}\\s]+");
    }
    public void getData(String tenKH,String id_donhang) {
        danh_sach_sp_da_mua.clear();
        adapter.notifyDataSetChanged();
        txtNoRes_InCheck.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getInstace().getService().getSPDaMua(id_donhang,tenKH).enqueue(new Callback<ArrayList<ThongTinKiemTra>>() {
            @Override
            public void onResponse(Call<ArrayList<ThongTinKiemTra>> call, Response<ArrayList<ThongTinKiemTra>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.body().size() > 0) {
                    int TongTien = 0;
                    for (int i = 0; i < response.body().size(); ++i) {
                        danh_sach_sp_da_mua.add(response.body().get(i));
                        TongTien += response.body().get(i).getGia() * response.body().get(i).getSL();
                    }
                    adapter.notifyDataSetChanged();
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    txtTong.setText(decimalFormat.format(TongTien));
                }
                else{
                    txtNoRes_InCheck.setVisibility(View.VISIBLE);
                }
                btnKiemTra.setEnabled(true);
            }


            @Override
            public void onFailure(Call<ArrayList<ThongTinKiemTra>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                btnKiemTra.setEnabled(true);
            }
        });
    }
    public void Mapping() {
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbarKiemTra);
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
