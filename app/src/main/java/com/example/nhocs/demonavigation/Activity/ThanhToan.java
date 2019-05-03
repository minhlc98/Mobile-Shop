package com.example.nhocs.demonavigation.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhocs.demonavigation.Model.Country;
import com.example.nhocs.demonavigation.Model.ShrPreferences;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.R;


public class ThanhToan extends AppCompatActivity {
    EditText[] edt=new EditText[4];
    Button btnXacNhan, btnHuy;
    Spinner list_countryCode;
    public final String info="info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Mapping();
        getInfoFromShPr();
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
                if (check_valid_info()){
                    String phoneNumber=list_countryCode.getSelectedItem().toString()+edt[1].getText().toString().trim();
                    String name=edt[0].getText().toString();
                    String address=edt[2].getText().toString();
                    String email=edt[3].getText().toString();
                    ThongTinNguoiDung thongTin=new ThongTinNguoiDung(name,list_countryCode.getSelectedItemPosition(),phoneNumber,address,email);
                    Intent intent=new Intent(ThanhToan.this,Activity_Authentication.class);
                    intent.putExtra(info,thongTin);
                    startActivity(intent);
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
        btnXacNhan=(Button) findViewById(R.id.btnXacNhan);
        btnHuy=(Button) findViewById(R.id.btnHuy);
        list_countryCode=(Spinner) findViewById(R.id.list_countryCode);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, Country.code);
        list_countryCode.setAdapter(adapter);

    }

    public boolean check_valid_info() {
        String[] validation=new String[4];//kiểm tra dữ liệu người dùng nhập vào có hợp lệ hay không
        validation[0]="[\\p{L}\\s]+";   //Tên khách hàng
        validation[1]="\\d{9,14}";    //Điện thoại
        validation[2]="[\\p{L}\\s\\d/,\\.]+"; //Địa chỉ
        validation[3]="\\w+@\\w+\\.[a-z]+";    //Email
        boolean all_right=true;
        for (int i=0;i<4;++i)
        {
            if (!edt[i].getText().toString().trim().matches(validation[i])) {
                edt[i].setError("Thông tin không hợp lệ");
                all_right=false;
            }
        }
        return all_right;
    }
    public void getInfoFromShPr(){
        ThongTinNguoiDung tt=ShrPreferences.getInstance(this).getInfo();
        if (tt != null){
            list_countryCode.setSelection(tt.getID_countryCode());
            edt[0].setText(tt.getFullName());
            edt[1].setText(tt.getPhoneNumber());
            edt[2].setText(tt.getAddress());
            edt[3].setText(tt.getEmail());
        }
    }
}
