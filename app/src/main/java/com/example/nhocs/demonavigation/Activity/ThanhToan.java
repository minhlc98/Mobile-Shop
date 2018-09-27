package com.example.nhocs.demonavigation.Activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Ulti.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ThanhToan extends AppCompatActivity {
    EditText[] edt=new EditText[4];
    ImageView[] syntax_error=new ImageView[4];
    Button btnXacNhan, btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Mapping();
        Event();
    }
    public void Event() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_valid_info()) {
                    Create_OrderID(edt[0].getText().toString().trim(), edt[1].getText().toString().trim(), edt[2].getText().toString().trim(), edt[3].getText().toString().trim(), Server.url_DonHang);
                }
                else{
                    Toast.makeText(ThanhToan.this,"Vui lòng điền thông tin hợp lệ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Mapping() {
        edt[0]=(EditText) findViewById(R.id.edtTen);
        edt[1]=(EditText) findViewById(R.id.edtDT);
        edt[2]=(EditText) findViewById(R.id.edtDiaChi);
        edt[3]=(EditText) findViewById(R.id.edtEmail);
        syntax_error[0]=(ImageView) findViewById(R.id.syntax_error_TenKH);
        syntax_error[1]=(ImageView) findViewById(R.id.syntax_error_DT);
        syntax_error[2]=(ImageView) findViewById(R.id.syntax_error_DiaChi);
        syntax_error[3]=(ImageView) findViewById(R.id.syntax_error_Email);
        btnXacNhan=(Button) findViewById(R.id.btnXacNhan);
        btnHuy=(Button) findViewById(R.id.btnHuy);
    }
    public void Create_OrderID(final String TenKH, final String DienThoai, final String DiaChi, final String Email, String url) {
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String id_donhang) {
                if (!id_donhang.toString().equals("fail")){
                    Create_Order_Detail(Server.url_ChiTietDonHang,id_donhang,dialog);
                }
                else{
                    Toast.makeText(ThanhToan.this, "Xin vui lòng kiểm tra lại kết nối", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThanhToan.this, "Xin vui lòng kiểm tra lại kết nối", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map=new HashMap<>();
                map.put("TenKH",TenKH);
                map.put("SoDT",DienThoai);
                map.put("DiaChi",DiaChi);
                map.put("Email",Email);
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,5,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
    public void Create_Order_Detail(String url, final String id_donhang, final Dialog dialog){
        RequestQueue queue=Volley.newRequestQueue(ThanhToan.this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.toString().equals("Success"))
                {
                    dialog.cancel();

                    MainActivity.database.Query("Delete from GIOHANG");//Xóa dữ liệu trong database
                    MainActivity.soluong_giohang=0;//chỉnh lại số 0 trên hình cart
                    Dialog dialog_success=new Dialog(ThanhToan.this);
                    dialog_success.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog_success.setCanceledOnTouchOutside(false);
                    dialog_success.setContentView(R.layout.pay_success);
                    TextView txtID=(TextView) dialog_success.findViewById(R.id.txtID);
                    txtID.append(String.valueOf(id_donhang));
                    ImageButton imageButton=(ImageButton) dialog_success.findViewById(R.id.image_close);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    dialog_success.show();
                }
                else{
                    dialog.cancel();
                    Toast.makeText(ThanhToan.this,"Kết nối thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThanhToan.this, "Vui lòng kiểm tra lại kết nối", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> m=new HashMap<>();
                JSONArray jsonArray=new JSONArray();
                for (int i = 0; i< GioHang.mang_gio_hang.size(); ++i) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("ID_DonHang",id_donhang);
                        jsonObject.put("ID_SanPham",GioHang.mang_gio_hang.get(i).getID());
                        jsonObject.put("Gia",GioHang.mang_gio_hang.get(i).getGia());
                        jsonObject.put("SoLuong",GioHang.mang_gio_hang.get(i).getSoLuong());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                m.put("json",jsonArray.toString());
                return m;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000,5,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }
    public boolean check_valid_info() {
        String[] validation=new String[4];//kiểm tra dữ liệu người dùng nhập vào có hợp lệ hay không
        validation[0]="[\\p{L}\\s]+";   //Tên khách hàng
        validation[1]="^0\\d{9,14}";    //Điện thoại
        validation[2]="[\\p{L}\\s\\d/,\\.]+"; //Địa chỉ
        validation[3]="\\w+@\\w+\\.[a-z]+";    //Email
        boolean all_right=true;
        for (int i=0;i<4;++i)
        {
            if (edt[i].getText().toString().trim().matches(validation[i])) syntax_error[i].setVisibility(View.INVISIBLE);
            else {
                syntax_error[i].setVisibility(View.VISIBLE);
                all_right=false;
            }
        }
        return all_right;
    }
}
