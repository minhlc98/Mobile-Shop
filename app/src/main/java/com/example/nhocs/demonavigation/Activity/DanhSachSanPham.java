package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Adapter.SanPhamAdapter;
import com.example.nhocs.demonavigation.Ulti.NetworkManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhSachSanPham extends AppCompatActivity {

    ArrayList<ThongTinSanPham> mang_san_pham;
    ListView lvSanPham;
    SanPhamAdapter adapter;
    android.support.v7.widget.Toolbar toolbarSanPham;
    int ID, page=1;
    boolean isLoading;
    View footerView;
    boolean limit;
    myHandler handler;
    TextView txt_count;
    ImageButton cart;
    MenuItem menuItem;
    LinearLayout layout_main,layout_no_connection;
    Button btnThuLai;
    SwipeRefreshLayout swipeRefreshLayout;
    boolean check_created;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);
        layout_main=(LinearLayout) findViewById(R.id.layout_main);
        layout_no_connection=(LinearLayout) findViewById(R.id.layout_no_connection);
        btnThuLai=(Button) findViewById(R.id.btnThuLai1);
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
        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue,R.color.green,R.color.red,R.color.yellow);
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
                            }
                            else{
                                layout_main.setVisibility(View.INVISIBLE);
                                layout_no_connection.setVisibility(View.VISIBLE);
                            }
                        }
                    }, 2000);
                }


        });
        if (NetworkManager.isOnline(getApplicationContext())) {
            loadAll();
        }
        else{
            layout_main.setVisibility(View.INVISIBLE);
            layout_no_connection.setVisibility(View.VISIBLE);
        }
    }
    public void loadAll(){
        if (!check_created) {
            Bundle bundle = getIntent().getExtras();
            ID = bundle.getInt("ID");
            String title = bundle.getString("title");
            Mapping();
            actionBar(title);
            Event();
            check_created=true;
        }
        myThread thread=new myThread();
        isLoading=true;
        thread.start();
    }
    public void Event(){
        lvSanPham.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lvSanPham.getChildAt(0) != null) {
                    swipeRefreshLayout.setEnabled(lvSanPham.getFirstVisiblePosition() == 0 && lvSanPham.getChildAt(0).getTop() == 0);
                }
                if (firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount!=0 && !isLoading && !limit){
                    myThread thread= new myThread();
                    isLoading=true;
                    thread.start();
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
        Intent intent1=new Intent(DanhSachSanPham.this,TimKiem.class);
        startActivity(intent1);
        return super.onOptionsItemSelected(item);
    }
    public void Mapping() {
        lvSanPham=(ListView) findViewById(R.id.lvSanPham);
        toolbarSanPham=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbarSanPham);
        mang_san_pham=new ArrayList<>();
        adapter=new SanPhamAdapter(this,R.layout.hien_thi_san_pham,mang_san_pham);
        lvSanPham.setAdapter(adapter);
        LayoutInflater inflater=getLayoutInflater();
        footerView=inflater.inflate(R.layout.progress_load,null);
        handler=new myHandler();
    }
    public void load_San_Pham() {
        Map<String,String> para=new HashMap<>();
        String ID_LoaiSP=String.valueOf(ID);
        para.put("id",ID_LoaiSP);
        para.put("page",String.valueOf(page));
        RetrofitClient.getInstace().getService().getSanPham(para).enqueue(new Callback<ArrayList<ThongTinSanPham>>() {
            @Override
            public void onResponse(Call<ArrayList<ThongTinSanPham>> call, Response<ArrayList<ThongTinSanPham>> response) {
                if (response.isSuccessful()){
                    ++page;//Cộng vào để lần sau load những sản phẩm tiếp theo
                    lvSanPham.removeFooterView(footerView);
                    if (response.body().size()>0) {
                        for (int i = 0; i < response.body().size(); ++i) {
                            mang_san_pham.add(response.body().get(i));
                        }
                        isLoading=false;
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        limit=true;
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ThongTinSanPham>> call, Throwable t) {
                Toast.makeText(DanhSachSanPham.this, "Không có kết nối", Toast.LENGTH_LONG).show();
                isLoading=false;
                lvSanPham.removeFooterView(footerView);
            }
        });
    }
    public void actionBar(String title) {
        setSupportActionBar(toolbarSanPham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        if (txt_count!=null) txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        menuItem=menu.findItem(R.id.item_store);
        View view=menuItem.getActionView();
        if (view!=null)
        {
            txt_count=view.findViewById(R.id.txt_count);
            cart=view.findViewById(R.id.cart);
        }
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DanhSachSanPham.this,GioHang.class);
                startActivity(intent);
            }
        });
        txt_count.setText(String.valueOf(MainActivity.soluong_giohang));
        return super.onCreateOptionsMenu(menu);
    }
    public class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1: lvSanPham.addFooterView(footerView);
                        load_San_Pham();
                        break;
            }
            super.handleMessage(msg);
        }
    }
    public class myThread extends Thread{
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
                super.run();
            }
    }
}
