package com.example.nhocs.demonavigation.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhocs.demonavigation.Activity.MainActivity;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ShrPreferences;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentLogin extends Fragment {
    private EditText edtAct, edtPass;
    private Button btnLogin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Mapping(v);
        Event();
        return v;
    }

    public void Mapping(View v) {
        edtAct = v.findViewById(R.id.edtAct);
        edtPass = v.findViewById(R.id.edtPass);
        btnLogin = v.findViewById(R.id.btnLogin);
    }

    public void Event() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.layout_loading);
                dialog.show();
                String acc = edtAct.getText().toString().trim();
                String pass = getSHA1Hash(edtPass.getText().toString().trim());
                RetrofitClient.getInstace().getService().getUser(acc, pass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<ThongTinNguoiDung>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(ThongTinNguoiDung result) {
                                if (result == null) {
                                    Toast.makeText(getActivity(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                                } else {
                                    ShrPreferences.getInstance(getActivity()).putInfo(result);
                                    MainActivity.isLogin = true;
                                    (getActivity()).finish();
                                }
                                dialog.cancel();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getActivity(), "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
//                        .enqueue(new Callback<ThongTinNguoiDung>() {
//                    @Override
//                    public void onResponse(Call<ThongTinNguoiDung> call, Response<ThongTinNguoiDung> response) {
//                        ThongTinNguoiDung res = response.body();
//                        if (res == null) {
//                            Toast.makeText(getActivity(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
//                        } else {
//                            ShrPreferences.getInstance(getActivity()).putInfo(res);
//                            MainActivity.isLogin = true;
////                            Intent intent=new Intent(getActivity(), MainActivity.class);
////                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                            startActivity(intent);
//                            (getActivity()).finish();
//                        }
//                        dialog.cancel();
//                    }
//
//                    @Override
//                    public void onFailure(Call<ThongTinNguoiDung> call, Throwable t) {
//                        Toast.makeText(getActivity(), "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
//                        dialog.cancel();
//                    }
//                });
            }
        });
    }

    private String getSHA1Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertByteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte datum : data) {
            sb.append(Integer.toString((datum & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
