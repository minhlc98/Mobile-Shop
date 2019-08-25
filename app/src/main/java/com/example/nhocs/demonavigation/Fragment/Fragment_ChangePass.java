package com.example.nhocs.demonavigation.Fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.nhocs.demonavigation.Activity.General_Info_Activity;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ShrPreferences;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Fragment_ChangePass extends Fragment {
    Button btnChangePass;
    ImageView imgBack;
    EditText edtOldPass, edtNewPass, edtConfirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
        Mapping(v);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((General_Info_Activity) getActivity()).removeFragmentChangePass();
            }
        });
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongTinNguoiDung tt = ShrPreferences.getInstance(getActivity()).getInfo();
                String oldPass = tt.getPass();
                String oldPass_Input = getSHA1Hash(edtOldPass.getText().toString());
                if (oldPass.equals(oldPass_Input)) {
                    String newPass = edtNewPass.getText().toString();
                    String confirm = edtConfirm.getText().toString();
                    if (newPass.equals(confirm)) {
                        final String newPass_hashed = getSHA1Hash(newPass);
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.setContentView(R.layout.layout_loading);
                        dialog.show();
                        int id = tt.getID();
                        RetrofitClient.getInstace().getService().changePass(id, newPass_hashed)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<String>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(String res) {
                                        if (res.equals("success")) {
                                            Toast.makeText(getActivity(), "Thay đổi thành công", Toast.LENGTH_LONG).show();
                                            //Cập nhật lại mật khẩu
                                            updateInfo(newPass_hashed);
                                            //Exit
                                            ((General_Info_Activity) getActivity()).removeFragmentChangePass();
                                        } else {
                                            Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
                                        }
                                        dialog.cancel();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                });
//                                .enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                try {
//                                    String res = response.body().string();
//                                    if (res.equals("success")) {
//                                        Toast.makeText(getActivity(), "Thay đổi thành công", Toast.LENGTH_LONG).show();
//                                        //Cập nhật lại mật khẩu
//                                        updateInfo(newPass_hashed);
//                                        //Exit
//                                        ((General_Info_Activity) getActivity()).removeFragmentChangePass();
//                                    } else {
//                                        Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
//                                    }
//                                    dialog.cancel();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                            }
//                        });
                    } else {
                        edtConfirm.setError("Mật khẩu không khớp");
                    }
                } else {
                    edtOldPass.setError("Mật khẩu cũ không đúng");
                }
            }
        });
        return v;
    }

    public void Mapping(View view) {
        btnChangePass = view.findViewById(R.id.btnChangePass);
        edtOldPass = view.findViewById(R.id.edtOldPass);
        edtNewPass = view.findViewById(R.id.edtNewPass);
        edtConfirm = view.findViewById(R.id.edtConfirmPass);
        imgBack = view.findViewById(R.id.imgBack);
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
        StringBuilder sb = new StringBuilder();
        for (byte datum : data) {
            sb.append(Integer.toString((datum & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public void updateInfo(String newPass) {
        ThongTinNguoiDung tt = ShrPreferences.getInstance(getActivity()).getInfo();
        tt.setPass(newPass);
        ShrPreferences.getInstance(getActivity()).putInfo(tt);
    }
}
