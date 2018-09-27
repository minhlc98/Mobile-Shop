package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.example.nhocs.demonavigation.Ulti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimKiem extends AppCompatActivity {

    EditText edtTimKiem;
    Toolbar toolbarTimKiem;
    ListView lvSanPhamTimThay;
    TextView txtNoRes;
    View footerView;
    ArrayList<SanPham> mang_san_pham_tim_thay;
    boolean isLoading,limit;
    SanPhamAdapter adapter;
    int page=1;
    MyHandler myHandler;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        Mapping();
        actionBar();
        Event();
    }
    public void Event() {
        edtTimKiem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    name=edtTimKiem.getText().toString().trim().replace(" ","%20");
                    name=name.replace("%27","");
                    page=1;
                    limit=false;
                    txtNoRes.setVisibility(View.INVISIBLE);
                    mang_san_pham_tim_thay.clear();
                    adapter.notifyDataSetChanged();
                    MyThread myThread=new MyThread();
                    isLoading=true;
                    myThread.start();
                    return true;
                }
                return false;
            }
        });

        lvSanPhamTimThay.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount!=0 && !isLoading && !limit){
                    MyThread thread= new MyThread();
                    isLoading=true;
                    thread.start();
                }
            }
        });
        lvSanPhamTimThay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TimKiem.this, ChiTietSanPham.class);
                intent.putExtra("ThongTinSanPham",mang_san_pham_tim_thay.get(position));
                intent.putExtra("from_search",true);
                startActivity(intent);
            }
        });
    }
    public void actionBar() {
        setSupportActionBar(toolbarTimKiem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTimKiem.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarTimKiem.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void load_San_Pham(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() != 0) {
                    lvSanPhamTimThay.removeFooterView(footerView);
                    for (int i = 0; i < response.length(); ++i) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            SanPham sp = new SanPham(jsonObject.getInt("ID"),
                                    jsonObject.getInt("Gia"),
                                    jsonObject.getString("TenSP"),
                                    jsonObject.getString("Hinh"),
                                    jsonObject.getString("MoTa"));
                            mang_san_pham_tim_thay.add(sp);
                            adapter.notifyDataSetChanged();
                            isLoading=false;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                else{
                    if (page==2) txtNoRes.setVisibility(View.VISIBLE);
                    limit=true;
                    lvSanPhamTimThay.removeFooterView(footerView);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lvSanPhamTimThay.removeFooterView(footerView);
                isLoading=false;
                --page;
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void Mapping() {
        edtTimKiem=(EditText) findViewById(R.id.edtTimKiem);
        toolbarTimKiem=(Toolbar) findViewById(R.id.toolbarTimKiem);
        lvSanPhamTimThay=(ListView) findViewById(R.id.lvSanPhamTimThay);
        txtNoRes=(TextView) findViewById(R.id.txtNoRes);
        txtNoRes.setVisibility(View.INVISIBLE);
        lvSanPhamTimThay.setVisibility(View.VISIBLE);
        LayoutInflater inflater=getLayoutInflater();
        footerView=inflater.inflate(R.layout.progress_load,null);
        mang_san_pham_tim_thay=new ArrayList<>();
        adapter=new SanPhamAdapter(this,R.layout.hien_thi_san_pham,mang_san_pham_tim_thay);
        lvSanPhamTimThay.setAdapter(adapter);
        myHandler=new MyHandler();
    }
    public String formatURL(int page) {
        return Server.url_TimKiem+String.format("?name=%s&page=%d",name,page);
    }
    public class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1: lvSanPhamTimThay.addFooterView(footerView);
                load_San_Pham(formatURL(page++));
                break;
            }
            super.handleMessage(msg);
        }
    }
    public class MyThread extends Thread{
        @Override
        public void run() {
            myHandler.sendEmptyMessage(1);
            super.run();
        }
    }
}
