package com.example.nhocs.demonavigation.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhocs.demonavigation.Activity.MainActivity;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ShrPreferences;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLogin extends Fragment {
    EditText edtAct, edtPass;
    Button btnLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login,container,false);
        Mapping(v);
        Event();
        return v;
    }
    public void Mapping(View v){
        edtAct=(EditText) v.findViewById(R.id.edtAct);
        edtPass=(EditText) v.findViewById(R.id.edtPass);
        btnLogin=(Button) v.findViewById(R.id.btnLogin);
    }
    public void Event(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.layout_loading);
                dialog.show();
                String acc=edtAct.getText().toString().trim();
                String pass=getSHA1Hash(edtPass.getText().toString().trim());
                RetrofitClient.getInstace().getService().getUser(acc,pass).enqueue(new Callback<ThongTinNguoiDung>() {
                    @Override
                    public void onResponse(Call<ThongTinNguoiDung> call, Response<ThongTinNguoiDung> response) {
                        ThongTinNguoiDung res=response.body();
                        if (res == null) {
                            Toast.makeText(getActivity(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                        }
                        else{
                            ShrPreferences.getInstance(getActivity()).putInfo(res);
                            MainActivity.isLogin=true;
//                            Intent intent=new Intent(getActivity(), MainActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
                            (getActivity()).finish();
                        }
                        dialog.cancel();
                    }

                    @Override
                    public void onFailure(Call<ThongTinNguoiDung> call, Throwable t) {
                        Toast.makeText(getActivity(), "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
            }
        });
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
