package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Model.SanPham;
import com.example.nhocs.demonavigation.Adapter.SanPhamAdapter;
import com.example.nhocs.demonavigation.Ulti.NetworkManager;
import com.example.nhocs.demonavigation.Ulti.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class DanhSachSanPham extends AppCompatActivity {

    ArrayList<SanPham> mang_san_pham;
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
                if (NetworkManager.check_Connection(getApplicationContext())) {
                    layout_no_connection.setVisibility(View.INVISIBLE);
                    layout_main.setVisibility(View.VISIBLE);
                    loadAll();
                }
            }
        });
        if (NetworkManager.check_Connection(getApplicationContext())) {
            loadAll();
        }
        else{
            layout_main.setVisibility(View.INVISIBLE);
            layout_no_connection.setVisibility(View.VISIBLE);
        }
    }
    public void loadAll(){
        Bundle bundle = getIntent().getExtras();
        ID = bundle.getInt("ID");
        String title = bundle.getString("title");
        Mapping();
        actionBar(title);
        Event();
    }
    public void Event(){
        lvSanPham.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
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
                Intent intent=new Intent(DanhSachSanPham.this,ChiTietSanPham.class);
                intent.putExtra("ThongTinSanPham",mang_san_pham.get(position));
                intent.putExtra("from_search",false);
                startActivity(intent);
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
        myThread thread=new myThread();
        isLoading=true;
        thread.start();
    }
    public void load_San_Pham(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() != 0) {
                    lvSanPham.removeFooterView(footerView);
                    for (int i = 0; i < response.length(); ++i) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            SanPham sp = new SanPham(jsonObject.getInt("ID"),
                                    jsonObject.getInt("Gia"),
                                    jsonObject.getString("TenSP"),
                                    jsonObject.getString("Hinh"),
                                    jsonObject.getString("MoTa"));
                            mang_san_pham.add(sp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    isLoading=false;
                    adapter.notifyDataSetChanged();
                }
                else{
                    limit=true;
                    lvSanPham.removeFooterView(footerView);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lvSanPham.removeFooterView(footerView);
                isLoading=false;
                --page;
            }
        });
        requestQueue.add(jsonArrayRequest);
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
    public String formatURL(int page) {
        return Server.url_getSanPham+String.format("?id=%d&page=%d",ID,page);
    }
    public class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1: lvSanPham.addFooterView(footerView);
                load_San_Pham(formatURL(page++));
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
