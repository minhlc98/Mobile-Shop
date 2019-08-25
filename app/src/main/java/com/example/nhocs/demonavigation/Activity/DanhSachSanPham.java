package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nhocs.demonavigation.Adapter.SanPhamAdapter;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Ulti.NetworkManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class DanhSachSanPham extends AppCompatActivity {

    ArrayList<ThongTinSanPham> mang_san_pham;
    ListView lvSanPham;
    SanPhamAdapter adapter;
    Toolbar toolbarSanPham;
    int ID, page = 1;
    boolean isLoading;
    View footerView;
    boolean limit;
    TextView txt_count;
    ImageButton cart;
    MenuItem menuItem;
    LinearLayout layout_main, layout_no_connection;
    Button btnThuLai;
    SwipeRefreshLayout swipeRefreshLayout;
    boolean check_created;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);
        layout_main = findViewById(R.id.layout_main);
        layout_no_connection = findViewById(R.id.layout_no_connection);
        btnThuLai = findViewById(R.id.btnThuLai1);
        btnThuLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkManager.isOnline(getApplicationContext())) {
                    layout_no_connection.setVisibility(View.INVISIBLE);
                    layout_main.setVisibility(View.VISIBLE);
                    loadAll();

                }
            }
        });
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.green, R.color.red, R.color.yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        if (NetworkManager.isOnline(getApplicationContext())) {
                            if (check_created) {
                                mang_san_pham.clear();
                                adapter.notifyDataSetChanged();
                                page = 1;
                                limit = false;
                            }
                            loadAll();
                            layout_main.setVisibility(View.VISIBLE);
                            layout_no_connection.setVisibility(View.INVISIBLE);
                        } else {
                            layout_main.setVisibility(View.INVISIBLE);
                            layout_no_connection.setVisibility(View.VISIBLE);
                        }
                    }
                }, 2000);
            }


        });
        if (NetworkManager.isOnline(getApplicationContext())) {
            loadAll();
        } else {
            layout_main.setVisibility(View.INVISIBLE);
            layout_no_connection.setVisibility(View.VISIBLE);
        }
    }

    public void loadAll() {
        if (!check_created) {
            Bundle bundle = getIntent().getExtras();
            ID = bundle.getInt("ID", 0);
            String title = bundle.getString("title");
            Mapping();
            actionBar(title);
            Event();
            check_created = true;
        }
        load_San_Pham();
//        myThread thread = new myThread();
//        isLoading = true;
//        thread.start();
    }

    public void Event() {
        lvSanPham.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lvSanPham.getChildAt(0) != null) {
                    swipeRefreshLayout.setEnabled(lvSanPham.getFirstVisiblePosition() == 0 && lvSanPham.getChildAt(0).getTop() == 0);
                }
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && !isLoading && !limit) {
//                    myThread thread = new myThread();
//                    thread.start();
                    load_San_Pham();
                }
            }
        });
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < mang_san_pham.size()) {
                    Intent intent = new Intent(DanhSachSanPham.this, ChiTietSanPham.class);
                    intent.putExtra("ThongTinSanPham", mang_san_pham.get(position));
                    intent.putExtra("from_search", false);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent1 = new Intent(DanhSachSanPham.this, TimKiem.class);
        startActivity(intent1);
        return super.onOptionsItemSelected(item);
    }

    public void Mapping() {
        lvSanPham = findViewById(R.id.lvSanPham);
        toolbarSanPham = findViewById(R.id.toolbarSanPham);
        mang_san_pham = new ArrayList<>();
        adapter = new SanPhamAdapter(this, R.layout.hien_thi_san_pham, mang_san_pham);
        lvSanPham.setAdapter(adapter);
        LayoutInflater inflater = getLayoutInflater();
        footerView = inflater.inflate(R.layout.progress_load, null);
        //  handler = new myHandler();
    }

    public void load_San_Pham() {
        isLoading = true;
        lvSanPham.addFooterView(footerView);
        Map<String, String> para = new HashMap<>();
        String ID_LoaiSP = String.valueOf(ID);
        para.put("id", ID_LoaiSP);
        para.put("page", String.valueOf(page));
        RetrofitClient.getInstace()
                .getService()
                .getSanPham(para)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<ThongTinSanPham>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<ThongTinSanPham> thongTinSanPhams) {
                        ++page;//Cộng vào để lần sau load những sản phẩm tiếp theo
                        lvSanPham.removeFooterView(footerView);
                        isLoading = false;
                        if (thongTinSanPhams.size() > 0) {
                            mang_san_pham.addAll(thongTinSanPhams);
                            adapter.notifyDataSetChanged();
                        } else {
                            limit = true;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(DanhSachSanPham.this, "Không có kết nối", Toast.LENGTH_LONG).show();
                        isLoading = false;
                        lvSanPham.removeFooterView(footerView);
                    }
                });

    }

    public void actionBar(String title) {
        setSupportActionBar(toolbarSanPham);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarSanPham.setTitle(title);
        toolbarSanPham.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarSanPham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        if (txt_count != null) txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menuItem = menu.findItem(R.id.item_store);
        View view = menuItem.getActionView();
        if (view != null) {
            txt_count = view.findViewById(R.id.txt_count);
            cart = view.findViewById(R.id.cart);
        }
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachSanPham.this, GioHang.class);
                startActivity(intent);
            }
        });
        txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
        return super.onCreateOptionsMenu(menu);
    }

}
