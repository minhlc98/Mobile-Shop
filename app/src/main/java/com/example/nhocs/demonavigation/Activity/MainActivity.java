package com.example.nhocs.demonavigation.Activity;


import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhocs.demonavigation.Adapter.ViewPagerAdapter;
import com.example.nhocs.demonavigation.Ulti.Database;
import com.example.nhocs.demonavigation.Model.LoaiSP;
import com.example.nhocs.demonavigation.Adapter.Menu_Adapter;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Model.SanPham;
import com.example.nhocs.demonavigation.Adapter.SanPhamMoiNhatAdapter;
import com.example.nhocs.demonavigation.Ulti.NetworkManager;
import com.example.nhocs.demonavigation.Ulti.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawer;
    RecyclerView recyclerView;
    ArrayList<LoaiSP> danh_sach_loai_san_pham;
    ArrayList<SanPham> danh_sach_san_pham;
    Menu_Adapter menu_adapter;
    SanPhamMoiNhatAdapter sanPhamAdapter;
    CountDownTimer countDownTimer;
    ViewPagerAdapter adapter;
    ViewPager viewPager;
    ArrayList<String> mang_hinh_anh;
    TextView txt_count;
    TextView[] dot;
    ImageButton cart;
    int previous_dot;
    MenuItem menuItem;
    Button btnThuLai;
    LinearLayout linearLayout;
    public static Database database;
    public static int soluong_giohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout=(LinearLayout) findViewById(R.id.linearLayout);
        btnThuLai=(Button) findViewById(R.id.btnThuLai);
        drawer=(DrawerLayout) findViewById(R.id.drawer);
        btnThuLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkManager.check_Connection(getApplicationContext())) {
                    linearLayout.setVisibility(View.INVISIBLE);
                    drawer.setVisibility(View.VISIBLE);
                    loadAll();
                }
            }
        });
        if (NetworkManager.check_Connection(getApplicationContext())){
            loadAll();
        }
        else{
            drawer.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }
    public void loadAll(){
        Mapping();
        actionBar();
        LoadAds();
        Menu_List(Server.url_getLoaiSanPham);
        load_SPMoiNhat(Server.url_getSanPhamMoiNhat);
        getSoLuong_GioHang();
        Event();
        setTime_to_ChangeViewPager();
    }
    public void setTime_to_ChangeViewPager(){
        countDownTimer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                switch(viewPager.getCurrentItem()){
                    case 0:viewPager.setCurrentItem(1);break;
                    case 1:viewPager.setCurrentItem(2);break;
                    case 2:viewPager.setCurrentItem(3);break;
                    case 3:viewPager.setCurrentItem(4);break;
                    default:viewPager.setCurrentItem(0);break;
                }
            }
        };
    }
    public void Event(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dot[previous_dot].setBackgroundResource(R.drawable.dot);
                dot[position].setBackgroundResource(R.drawable.active_dot);
                previous_dot=position;
                countDownTimer.cancel();
                countDownTimer.start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position!=0){
                    if (position==danh_sach_loai_san_pham.size()-2){
                        Intent intent=new Intent(MainActivity.this,LienHe.class);
                        startActivity(intent);
                    }else if (position==danh_sach_loai_san_pham.size()-1)
                    {
                        Intent intent=new Intent(MainActivity.this,ThongTin.class);
                        startActivity(intent);
                    }
                    else if (position==danh_sach_loai_san_pham.size()-3){
                        Intent intent=new Intent(MainActivity.this,KiemTra.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(MainActivity.this, DanhSachSanPham.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("ID", position);
                        bundle.putString("title", danh_sach_loai_san_pham.get(position).getTenSP());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                }
                drawer.closeDrawer(GravityCompat.START);

            }
        });
    }
    public void getSoLuong_GioHang() {
        Cursor cursor=database.getData("Select * from GIOHANG");
        soluong_giohang=cursor.getCount();
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
                Intent intent=new Intent(MainActivity.this,GioHang.class);
                startActivity(intent);
            }
        });
        txt_count.setText(String.valueOf(soluong_giohang));
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent1=new Intent(MainActivity.this,TimKiem.class);
        startActivity(intent1);
        return super.onOptionsItemSelected(item);
    }
    public void Mapping() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        navigationView=(NavigationView) findViewById(R.id.navigationView);
        listView=(ListView) findViewById(R.id.listView);
        recyclerView=findViewById(R.id.recyclerView);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        dot=new TextView[5];
        dot[0]=(TextView) findViewById(R.id.dot1);
        dot[1]=(TextView) findViewById(R.id.dot2);
        dot[2]=(TextView) findViewById(R.id.dot3);
        dot[3]=(TextView) findViewById(R.id.dot4);
        dot[4]=(TextView) findViewById(R.id.dot5);
        danh_sach_loai_san_pham=new ArrayList<>();
        mang_hinh_anh=new ArrayList<>();
        menu_adapter=new Menu_Adapter(this,R.layout.menu_navigation,danh_sach_loai_san_pham);
        listView.setAdapter(menu_adapter);
        danh_sach_san_pham=new ArrayList<>();
        sanPhamAdapter=new SanPhamMoiNhatAdapter(this,danh_sach_san_pham,R.layout.danh_sach_san_pham_moi_nhat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sanPhamAdapter);
        database=new Database(this,"DienThoaiDiDong.sqlite",null,1);

    }
    public void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }
    public void LoadAds() {
        mang_hinh_anh.add("http://minhlc98.000webhostapp.com/image1.jpg");
        mang_hinh_anh.add("http://minhlc98.000webhostapp.com/image2.jpg");
        mang_hinh_anh.add("http://minhlc98.000webhostapp.com/image3.jpg");
        mang_hinh_anh.add("http://minhlc98.000webhostapp.com/image4.jpg");
        mang_hinh_anh.add("http://minhlc98.000webhostapp.com/image5.jpg");
        adapter=new ViewPagerAdapter(mang_hinh_anh,this);
        viewPager.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        if (countDownTimer!=null) countDownTimer.start();
        if (txt_count!=null) txt_count.setText(String.valueOf(soluong_giohang));
        super.onResume();
    }
    @Override
    protected void onPause() {
        if (countDownTimer!=null)  countDownTimer.cancel();
        super.onPause();
    }
    public void Menu_List(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String urlTrangChu="http://www.apsaigonpetro.com/assets/images/home.png";
                danh_sach_loai_san_pham.add(new LoaiSP(0,"Trang Chủ", urlTrangChu));
                for (int i=0;i<response.length();++i)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        danh_sach_loai_san_pham.add(new LoaiSP(jsonObject.getInt("ID"),jsonObject.getString("TenSP"),jsonObject.getString("Hinh")));
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                //String urlDanhSachYeuThich="http://fotomelia.com/wp-content/uploads/edd/2016/01/image-gratuite-libre-de-droit-creative-commoons-CCo-20-1560x1154.png";
                String urlLienHe="https://images.vexels.com/media/users/3/134904/isolated/preview/55500571b8176366dd4298b925235cb2-3d-contact-support-icon-by-vexels.png";
                String urlThongTin="http://www.cooperacion2005.es/wp-content/uploads/2015/09/11949858491812712425information_sign_mo_01.svg_.hi_.png";
                String urlKiemTra="https://eventis-sauerland.de/wp-content/uploads/2017/05/icon-803718_640.png";
                //danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(),"Sản phẩm yêu thích",urlDanhSachYeuThich));
                danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(),"Kiểm tra",urlKiemTra));
                danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(),"Liên hệ",urlLienHe));
                danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(),"Thông tin",urlThongTin));
                menu_adapter.notifyDataSetChanged();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        }
        );
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000,5,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }
    public void load_SPMoiNhat(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();++i)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        SanPham sp=new SanPham(jsonObject.getInt("ID"),
                                jsonObject.getInt("Gia"),
                                jsonObject.getString("TenSP"),
                                jsonObject.getString("Hinh"),
                                jsonObject.getString("MoTa"));
                        danh_sach_san_pham.add(sp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                sanPhamAdapter.notifyDataSetChanged();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000,5,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }
}
