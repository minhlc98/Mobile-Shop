package com.example.nhocs.demonavigation.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhocs.demonavigation.Adapter.SP_DaMua_Adapter;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ThongTinKiemTra;
import com.example.nhocs.demonavigation.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class KiemTra extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBar;
    TextView txtNoRes_InCheck, txtTong, txtLoi;
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

    public void Event() {
        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLoi.setVisibility(View.INVISIBLE);
                String ID_DonHang = edtIDDonHang.getText().toString();
                String TenKH = edtTenKH.getText().toString().trim();
                if (check_valid_info(ID_DonHang, TenKH)) {
                    txtTong.setText("0đ");
                    getData(TenKH, ID_DonHang);//lấy sản phẩm khách hàng đã đặt từ database
                } else {
                    txtLoi.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public boolean check_valid_info(String ID_DonHang, String TenKH) {
        return ID_DonHang.matches("\\d+") && TenKH.matches("[\\p{L}\\s]+");
    }

    public void getData(String tenKH, String id_donhang) {
        danh_sach_sp_da_mua.clear();
        adapter.notifyDataSetChanged();
        txtNoRes_InCheck.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        btnKiemTra.setEnabled(false);
        RetrofitClient.getInstace().getService()
                .getSPDaMua(id_donhang, tenKH)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<ThongTinKiemTra>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<ThongTinKiemTra> thongTinKiemTras) {
                        if (thongTinKiemTras.size() > 0) {
                            int TongTien = 0;
                            for (int i = 0; i < thongTinKiemTras.size(); ++i) {
                                ThongTinKiemTra tt = thongTinKiemTras.get(i);
                                danh_sach_sp_da_mua.add(tt);
                                TongTien += tt.getGia() * tt.getSL();
                            }
                            adapter.notifyDataSetChanged();
                            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                            txtTong.setText(decimalFormat.format(TongTien));
                        } else {
                            txtNoRes_InCheck.setVisibility(View.VISIBLE);
                        }
                        progressBar.setVisibility(View.GONE);
                        btnKiemTra.setEnabled(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.INVISIBLE);
                        btnKiemTra.setEnabled(true);
                    }
                });
    }

    public void Mapping() {
        toolbar = findViewById(R.id.toolbarKiemTra);
        progressBar = findViewById(R.id.progress_bar);
        txtNoRes_InCheck = findViewById(R.id.txtNoRes_InCheck);
        txtLoi = findViewById(R.id.txtLoi);
        edtIDDonHang = findViewById(R.id.edtIDDonHang);
        edtTenKH = findViewById(R.id.edtTenKH);
        btnKiemTra = findViewById(R.id.btnKiemTra);
        danh_sach_sp_da_mua = new ArrayList<>();
        lvDanhSachSP_DaMua = findViewById(R.id.lvDanhSachSP);
        adapter = new SP_DaMua_Adapter(this, R.layout.san_pham_da_mua, danh_sach_sp_da_mua);
        lvDanhSachSP_DaMua.setAdapter(adapter);
        txtTong = findViewById(R.id.txtTong);
    }

    public void actionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
