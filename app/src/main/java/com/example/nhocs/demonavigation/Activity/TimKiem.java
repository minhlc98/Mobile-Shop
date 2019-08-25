package com.example.nhocs.demonavigation.Activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhocs.demonavigation.Adapter.SanPhamAdapter;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;
import com.example.nhocs.demonavigation.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimKiem extends AppCompatActivity {

    EditText edtTimKiem;
    Toolbar toolbarTimKiem;
    ListView lvSanPhamTimThay;
    TextView txtNoRes, tvNumOfResult;
    View footerView;
    ArrayList<ThongTinSanPham> mang_san_pham_tim_thay;
    boolean isLoading, limit;
    SanPhamAdapter adapter;
    int page = 1;
    //MyHandler myHandler;
    String name;
    ImageView imgVoice;
    public final int REQ_CODE_SPEECH_INPUT = 100;
    LinearLayout layout_result;

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
                    name = edtTimKiem.getText().toString().trim().replace(" ", "%20");
                    getDataFromEditText();
                    closeKeyboard();
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
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && !isLoading && !limit) {
//                    MyThread thread= new MyThread();
//                    isLoading=true;
//                    thread.start();
                    load_San_Pham();
                }
            }
        });
        lvSanPhamTimThay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < mang_san_pham_tim_thay.size()) {
                    Intent intent = new Intent(TimKiem.this, ChiTietSanPham.class);
                    intent.putExtra("ThongTinSanPham", mang_san_pham_tim_thay.get(position));
                    intent.putExtra("from_search", true);
                    startActivity(intent);
                }
            }
        });
        imgVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && null != data) {

                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                edtTimKiem.setText(result.get(0));
                getDataFromEditText();
            }
        }
    }

    public void actionBar() {
        setSupportActionBar(toolbarTimKiem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTimKiem.setNavigationIcon(R.drawable.ic_back_24dp);
        toolbarTimKiem.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getDataFromEditText() {
        name = edtTimKiem.getText().toString().trim();
        page = 1;
        limit = false;
        tvNumOfResult.setText("");
        txtNoRes.setVisibility(View.INVISIBLE);
        layout_result.setVisibility(View.VISIBLE);
        mang_san_pham_tim_thay.clear();
        adapter.notifyDataSetChanged();
        getNumOfResult();
        load_San_Pham();
//        MyThread myThread = new MyThread();
//        isLoading = true;
//        myThread.start();
    }

    public void closeKeyboard() {
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void getNumOfResult() {
        RetrofitClient.getInstace().getService()
                .getNumOfRes(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        String res = "Có " + s + " kết quả";
                        tvNumOfResult.setText(res);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(TimKiem.this, "Không có kết nối", Toast.LENGTH_SHORT).show();
                    }
                });
//        RetrofitClient.getInstace().getService().getNumOfRes(name).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                if (response.isSuccessful()){
//                    try {
//                        String res="Có "+response.body().string()+" kết quả";
//                        tvNumOfResult.setText(res);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(TimKiem.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void load_San_Pham() {
        isLoading = true;
        lvSanPhamTimThay.addFooterView(footerView);
        Map<String, String> para = new HashMap<>();
        para.put("name", name);
        para.put("page", String.valueOf(page));
        RetrofitClient.getInstace()
                .getService()
                .getKetQua(para)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<ThongTinSanPham>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<ThongTinSanPham> thongTinSanPhams) {
                        ++page;
                        isLoading = false;
                        lvSanPhamTimThay.removeFooterView(footerView);
                        if (thongTinSanPhams.size() > 0) {
                            mang_san_pham_tim_thay.addAll(thongTinSanPhams);
                            adapter.notifyDataSetChanged();
                        } else {
                            if (page == 2) {
                                txtNoRes.setVisibility(View.VISIBLE);//khi page =2 có nghĩa là không tìm thấy sản phẩm
                                // vì ban đầu khai báo là 1 để tiện load more, khi load 1 lần sẽ page++ lên
                                layout_result.setVisibility(View.INVISIBLE);
                            }
                            limit = true;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(TimKiem.this, "Không có kết nối", Toast.LENGTH_SHORT).show();
                        lvSanPhamTimThay.removeFooterView(footerView);
                        isLoading = false;
                    }
                });
    }

    public void Mapping() {
        edtTimKiem = findViewById(R.id.edtTimKiem);
        toolbarTimKiem = findViewById(R.id.toolbarTimKiem);
        lvSanPhamTimThay = findViewById(R.id.lvSanPhamTimThay);
        txtNoRes = findViewById(R.id.txtNoRes);
        txtNoRes.setVisibility(View.INVISIBLE);
        lvSanPhamTimThay.setVisibility(View.VISIBLE);
        imgVoice = findViewById(R.id.imgVoice);
        layout_result = findViewById(R.id.layout_result);
        tvNumOfResult = findViewById(R.id.tvNumofResult);
        LayoutInflater inflater = LayoutInflater.from(this);
        footerView = inflater.inflate(R.layout.progress_load, null);
        mang_san_pham_tim_thay = new ArrayList<>();
        adapter = new SanPhamAdapter(this, R.layout.hien_thi_san_pham, mang_san_pham_tim_thay);
        lvSanPhamTimThay.setAdapter(adapter);
    }
}
