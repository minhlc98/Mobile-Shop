package com.example.nhocs.demonavigation.Activity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.nhocs.demonavigation.Adapter.Menu_Adapter;
import com.example.nhocs.demonavigation.Adapter.SanPhamMoiNhatAdapter;
import com.example.nhocs.demonavigation.Adapter.ViewPagerAdapter;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.Banner;
import com.example.nhocs.demonavigation.Model.LoaiSP;
import com.example.nhocs.demonavigation.Model.ShrPreferences;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Ulti.Database;
import com.example.nhocs.demonavigation.Ulti.NetworkManager;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    //NavigationView navigationView;
    ListView listView;
    DrawerLayout drawer;
    RecyclerView recyclerView;
    ArrayList<LoaiSP> danh_sach_loai_san_pham;
    Menu_Adapter menu_adapter;
    SanPhamMoiNhatAdapter sanPhamAdapter;
    CountDownTimer countDownTimer;
    ViewPagerAdapter adapter;
    ViewPager viewPager;
    TextView txt_count;
    ArrayList<TextView> dot;
    ImageButton cart;
    int previous_dot;
    MenuItem menuItem;
    Button btnThuLai;
    LinearLayout linearLayout, myDot;
    public static Database database;
    public static int soluong_giohang;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText edtSearch;
    long time_backPressed;
    public final String dbName = "DienThoaiDiDong.sqlite";
    boolean check_created;
    public static boolean isLogin;//để tránh tạo tạo 1 số cái như mapping, setTime_To_Change_Page, actionBar,event,get_SoLuong_GioHang
    //Vì khi rớt mạng mà refresh sẽ cho layout noInternet, khi người dùng có mạng r và click vào nút thử lại hoặc kéo refresh
    //thì sẽ load lại nhưng sẽ k chạy lại 1 sẽ hàm nói trên

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearLayout);
        btnThuLai = findViewById(R.id.btnThuLai);
        drawer = findViewById(R.id.drawer);
        btnThuLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkManager.isOnline(getApplicationContext())) {
                    linearLayout.setVisibility(View.INVISIBLE);
                    drawer.setVisibility(View.VISIBLE);
                    loadAll();
                    check_created = true;
                }
            }
        });
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.green, R.color.red, R.color.yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (check_created) {//nếu dữ liệu đã đc tạo thì mới clear
                            viewPager.setCurrentItem(0);
                            danh_sach_loai_san_pham.clear();
                            menu_adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(null);
                            viewPager.setAdapter(null);
                            countDownTimer.cancel();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                        if (NetworkManager.isOnline(getApplicationContext())) {
                            loadAll();
                            drawer.setVisibility(View.VISIBLE);
                            linearLayout.setVisibility(View.INVISIBLE);

                        } else {
                            linearLayout.setVisibility(View.VISIBLE);
                            drawer.setVisibility(View.INVISIBLE);
                        }
                    }
                }, 2000);
            }

        });

        if (NetworkManager.isOnline(getApplicationContext())) {
            loadAll();
            check_created = true;
        } else {
            drawer.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);

        }
    }

    public void loadAll() {
        if (!check_created) {
            Mapping();
            actionBar();
            Event();
            getSoLuong_GioHang();
            setTime_to_ChangeViewPager();
        } else {
            countDownTimer.start();
        }
        LoadAds();
        Menu_List();
        getSanPhamMoiNhat();
    }

    public void setTime_to_ChangeViewPager() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        viewPager.setCurrentItem(1);
                        break;
                    case 1:
                        viewPager.setCurrentItem(2);
                        break;
                    case 2:
                        viewPager.setCurrentItem(3);
                        break;
                    case 3:
                        viewPager.setCurrentItem(4);
                        break;
                    case 4:
                        viewPager.setCurrentItem(5);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                        break;
                }
            }
        }.start();
    }

    public void Event() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dot.get(previous_dot).setBackgroundResource(R.drawable.dot);
                dot.get(position).setBackgroundResource(R.drawable.active_dot);
                previous_dot = position;
                countDownTimer.cancel();
                countDownTimer.start();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //các giá trị 1 2 0
                //Giá trị 0 là lúc không đụng gì nữa
                //2 trang thái khi là kéo qua
                //1 là khi kéo xong k còn làm gì nữa sẽ ra 1 sau đó chuyển sang trang thái 0
                if (state != 0) swipeRefreshLayout.setEnabled(false);
                else swipeRefreshLayout.setEnabled(true);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position != 1) {
                    if (position == 0) {
                        if (isLogin) {
                            Intent intent = new Intent(MainActivity.this, General_Info_Activity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    } else if (position == danh_sach_loai_san_pham.size() - 2) {
                        Intent intent = new Intent(MainActivity.this, LienHe.class);
                        startActivity(intent);
                    } else if (position == danh_sach_loai_san_pham.size() - 1) {
                        Intent intent = new Intent(MainActivity.this, ThongTin.class);
                        startActivity(intent);
                    } else if (position == danh_sach_loai_san_pham.size() - 3) {
                        Intent intent = new Intent(MainActivity.this, KiemTra.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, DanhSachSanPham.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("ID", position - 1);
                        bundle.putString("title", danh_sach_loai_san_pham.get(position).getTenSP());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                }
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                swipeRefreshLayout.setEnabled(false);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                swipeRefreshLayout.setEnabled(true);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimKiem.class);
                startActivity(intent);
            }
        });
    }

    public void getSoLuong_GioHang() {
        Cursor cursor = database.getData("Select * from GIOHANG");
        soluong_giohang = cursor.getCount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.getItem(0).setVisible(false);
        menuItem = menu.findItem(R.id.item_store);
        View view = menuItem.getActionView();
        if (view != null) {
            txt_count = view.findViewById(R.id.txt_count);
            cart = view.findViewById(R.id.cart);
        }
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GioHang.class);
                startActivity(intent);
            }
        });
        txt_count.setText(String.valueOf(soluong_giohang));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent1 = new Intent(MainActivity.this, TimKiem.class);
        startActivity(intent1);
        return super.onOptionsItemSelected(item);
    }

    public void Mapping() {
        toolbar = findViewById(R.id.toolbar);
        // navigationView=(NavigationView) findViewById(R.id.navigationView);
        listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);
        viewPager = findViewById(R.id.viewPager);
        edtSearch = findViewById(R.id.edtSearch);
        dot = new ArrayList<>();
        myDot = findViewById(R.id.myDot);
        danh_sach_loai_san_pham = new ArrayList<>();
        menu_adapter = new Menu_Adapter(this, R.layout.menu_navigation, danh_sach_loai_san_pham);
        listView.setAdapter(menu_adapter);
        listView.setAdapter(menu_adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        database = new Database(this, dbName, null, 1);
    }

    public void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    public void LoadAds() {
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(24, 24);
        RetrofitClient.getInstace()
                .getService()
                .getQuangCao()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<Banner> banners) {
                        myDot.removeAllViews();
                        adapter = new ViewPagerAdapter(banners, MainActivity.this);
                        viewPager.setAdapter(adapter);
                        for (int i = 0; i < banners.size(); ++i) {
                            TextView tv = new TextView(MainActivity.this);
                            if (i == 0) tv.setBackgroundResource(R.drawable.active_dot);
                            else tv.setBackgroundResource(R.drawable.dot);
                            tv.setLayoutParams(params);
                            params.setMargins(20, 20, 20, 20);
                            dot.add(tv);
                            myDot.addView(dot.get(i));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Không có kết nối", Toast.LENGTH_LONG).show();
                    }
                });
//        RetrofitClient.getInstace().getService().getQuangCao().enqueue(new Callback<ArrayList<Banner>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Banner>> call, retrofit2.Response<ArrayList<Banner>> response) {
//                if (response.isSuccessful()) {
//                    myDot.removeAllViews();
//                    adapter = new ViewPagerAdapter(response.body(), MainActivity.this);
//                    viewPager.setAdapter(adapter);
//                    for (int i = 0; i < response.body().size(); ++i) {
//                        TextView tv = new TextView(MainActivity.this);
//                        if (i == 0) tv.setBackgroundResource(R.drawable.active_dot);
//                        else tv.setBackgroundResource(R.drawable.dot);
//                        tv.setLayoutParams(params);
//                        params.setMargins(20, 20, 20, 20);
//                        dot.add(tv);
//                        myDot.addView(dot.get(i));
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Banner>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Không có kết nối", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    protected void onResume() {
        // if (countDownTimer!=null) countDownTimer.start();
        if (txt_count != null) txt_count.setText(String.valueOf(soluong_giohang));
//        ThongTinNguoiDung tt=ShrPreferences.getInstance(MainActivity.this).getInfo();
//        if (tt != null) {
//            String oldImage = danh_sach_loai_san_pham.get(0).getHinhAnh();
//            String oldName = danh_sach_loai_san_pham.get(0).getTenSP();
//            String currentImage = "http://minhlc.000webhostapp.com/" + tt.getImage();//image if has change
//            if (!oldImage.equals(currentImage) || !oldName.equals(tt.getFullName())) {
//                danh_sach_loai_san_pham.set(0, new LoaiSP(0, tt.getFullName(), currentImage));
//                menu_adapter.notifyDataSetChanged();
//            }
//        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (countDownTimer != null) countDownTimer.cancel();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        //index 0 is user name
        ThongTinNguoiDung tt = ShrPreferences.getInstance(MainActivity.this).getInfo();
        if (danh_sach_loai_san_pham.size() > 0) {
            if (tt != null) {
                String oldImage = danh_sach_loai_san_pham.get(0).getHinhAnh();
                String oldName = danh_sach_loai_san_pham.get(0).getTenSP();
                String currentImage = "http://minhlc.000webhostapp.com/" + tt.getImage();//image if has change
                if (!oldImage.equals(currentImage) || !oldName.equals(tt.getFullName())) {
                    danh_sach_loai_san_pham.set(0, new LoaiSP(0, tt.getFullName(), currentImage));
                    menu_adapter.notifyDataSetChanged();
                }

            } else {
                String urlLogin = "https://employeebenefits.com/images/login_icon.png";
                danh_sach_loai_san_pham.set(0, new LoaiSP(0, "Đăng nhập", urlLogin));
                menu_adapter.notifyDataSetChanged();
            }
        }
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        if (time_backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();
        }
        time_backPressed = System.currentTimeMillis();
    }

    public void Menu_List() {
        RetrofitClient.getInstace()
                .getService()
                .getLoaiSanPham()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<LoaiSP>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<LoaiSP> loaiSPS) {
                        if (danh_sach_loai_san_pham.size() > 0) danh_sach_loai_san_pham.clear();
                        ThongTinNguoiDung tt = ShrPreferences.getInstance(MainActivity.this).getInfo();
                        if (tt != null) {
                            String urlProfile = "http://minhlc.000webhostapp.com/" + tt.getImage();
                            danh_sach_loai_san_pham.add(new LoaiSP(0, tt.getFullName(), urlProfile));
                            isLogin = true;
                        } else {
                            String urlLogin = "https://employeebenefits.com/images/login_icon.png";
                            danh_sach_loai_san_pham.add(new LoaiSP(0, "Đăng nhập", urlLogin));
                        }
                        String urlTrangChu = "http://www.apsaigonpetro.com/assets/images/home.png";
                        danh_sach_loai_san_pham.add(new LoaiSP(1, "Trang Chủ", urlTrangChu));

                        danh_sach_loai_san_pham.addAll(loaiSPS);
                        String urlLienHe = "https://images.vexels.com/media/users/3/134904/isolated/preview/55500571b8176366dd4298b925235cb2-3d-contact-support-icon-by-vexels.png";
                        String urlThongTin = "http://www.cooperacion2005.es/wp-content/uploads/2015/09/11949858491812712425information_sign_mo_01.svg_.hi_.png";
                        String urlKiemTra = "https://eventis-sauerland.de/wp-content/uploads/2017/05/icon-803718_640.png";
                        danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(), "Kiểm tra", urlKiemTra));
                        danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(), "Liên hệ", urlLienHe));
                        danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(), "Thông tin", urlThongTin));
                        menu_adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
//        RetrofitClient.getInstace().getService().getLoaiSanPham().enqueue(new Callback<ArrayList<LoaiSP>>() {
//            @Override
//            public void onResponse(Call<ArrayList<LoaiSP>> call, retrofit2.Response<ArrayList<LoaiSP>> response) {
//                if (response.isSuccessful()) {
//                    if (danh_sach_loai_san_pham.size() > 0) danh_sach_loai_san_pham.clear();
//                    ThongTinNguoiDung tt = ShrPreferences.getInstance(MainActivity.this).getInfo();
//                    if (tt != null) {
//                        String urlProfile = "http://minhlc.000webhostapp.com/" + tt.getImage();
//                        danh_sach_loai_san_pham.add(new LoaiSP(0, tt.getFullName(), urlProfile));
//                        isLogin = true;
//                    } else {
//                        String urlLogin = "https://employeebenefits.com/images/login_icon.png";
//                        danh_sach_loai_san_pham.add(new LoaiSP(0, "Đăng nhập", urlLogin));
//                    }
//                    String urlTrangChu = "http://www.apsaigonpetro.com/assets/images/home.png";
//                    danh_sach_loai_san_pham.add(new LoaiSP(1, "Trang Chủ", urlTrangChu));
//
//                    for (int i = 0; i < response.body().size(); ++i) {
//                        danh_sach_loai_san_pham.add(response.body().get(i));
//                    }
//                    String urlLienHe = "https://images.vexels.com/media/users/3/134904/isolated/preview/55500571b8176366dd4298b925235cb2-3d-contact-support-icon-by-vexels.png";
//                    String urlThongTin = "http://www.cooperacion2005.es/wp-content/uploads/2015/09/11949858491812712425information_sign_mo_01.svg_.hi_.png";
//                    String urlKiemTra = "https://eventis-sauerland.de/wp-content/uploads/2017/05/icon-803718_640.png";
//                    danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(), "Kiểm tra", urlKiemTra));
//                    danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(), "Liên hệ", urlLienHe));
//                    danh_sach_loai_san_pham.add(new LoaiSP(danh_sach_loai_san_pham.size(), "Thông tin", urlThongTin));
//                    menu_adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<LoaiSP>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Không có kết nối", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    public void getSanPhamMoiNhat() {
        RetrofitClient.getInstace()
                .getService()
                .getSanPhamMoiNhat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<ThongTinSanPham>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<ThongTinSanPham> thongTinSanPhams) {
                        sanPhamAdapter = new SanPhamMoiNhatAdapter(MainActivity.this, thongTinSanPhams, R.layout.san_pham_moi_nhat);
                        recyclerView.setAdapter(sanPhamAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Không có kết nối", Toast.LENGTH_LONG).show();
                    }
                });
//        RetrofitClient.getInstace().getService().getSanPhamMoiNhat().enqueue(new Callback<ArrayList<ThongTinSanPham>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ThongTinSanPham>> call, retrofit2.Response<ArrayList<ThongTinSanPham>> response) {
//                sanPhamAdapter=new SanPhamMoiNhatAdapter(MainActivity.this,response.body(),R.layout.san_pham_moi_nhat);
//                recyclerView.setAdapter(sanPhamAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ThongTinSanPham>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Không có kết nối", Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
