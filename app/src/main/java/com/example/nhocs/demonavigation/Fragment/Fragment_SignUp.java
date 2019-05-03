package com.example.nhocs.demonavigation.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhocs.demonavigation.Activity.LoginActivity;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.Country;
import com.example.nhocs.demonavigation.R;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_SignUp extends Fragment {
    EditText edtFullName, edtAcc, edtPass, edtAddr, edtEmail, edtPhone;
    RadioButton radioMale, radioFemale;
    Button btnSignup;
    Spinner list_countryCode;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_signup,container,false);
        Mapping(v);
        Event();
        return v;
    }
    public void Mapping(View view){
        edtFullName=(EditText) view.findViewById(R.id.fullname);
        edtAcc=(EditText) view.findViewById(R.id.account);
        edtPass=(EditText) view.findViewById(R.id.pswd);
        edtAddr=(EditText) view.findViewById(R.id.address);
        edtEmail=(EditText) view.findViewById(R.id.mal);
        edtPhone=(EditText) view.findViewById(R.id.phone);
        radioMale=(RadioButton) view.findViewById(R.id.radioMale);
        radioFemale=(RadioButton) view.findViewById(R.id.radioFemale);
        btnSignup=(Button) view.findViewById(R.id.act);
        list_countryCode=(Spinner) view.findViewById(R.id.list_code);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item, Country.code);
        list_countryCode.setAdapter(adapter);
    }
    public void Event(){
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.layout_loading);
                dialog.show();
                checkExistAcc(dialog,edtAcc.getText().toString().trim());
                fragLogin();
            }

        });

    }
    public void mSignUp(final Dialog dialog){
        String fullName = edtFullName.getText().toString().trim();
        String account = edtAcc.getText().toString().trim();
        String password = getSHA1Hash(edtPass.getText().toString().trim());//Hash SHA1
        String address = edtAddr.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        int code=list_countryCode.getSelectedItemPosition();
        String phone = edtPhone.getText().toString().trim();
        int sex = radioMale.isChecked() ? 1 : 0;
        RetrofitClient.getInstace().getService().createUser(fullName,account,password,email,address,code,phone,sex).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result=response.body().string();
                    if (result.equals("success")){
                        dialog.cancel();
                        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                        fragLogin();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void checkExistAcc(final Dialog dialog, String account){
        RetrofitClient.getInstace().getService().checkExist(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result=response.body().string();
                    if (result.equals("success")) mSignUp(dialog);
                    else if (result.equals("exist")){
                        Toast.makeText(getActivity(), "Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getActivity(), "Đã xảy ra lỗi. Vui lòng thử lại", Toast.LENGTH_LONG).show();
                    }
                    dialog.cancel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
    }
    public void fragLogin(){
        ((LoginActivity)getActivity()).getFragLogin();
    }
    public String getSHA1Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String convertByteToHex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
