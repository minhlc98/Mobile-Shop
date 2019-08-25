package com.example.nhocs.demonavigation.Fragment;

import android.app.Dialog;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhocs.demonavigation.Activity.LoginActivity;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.Country;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Fragment_SignUp extends Fragment {
    private EditText edtFullName, edtAcc, edtPass, edtAddr, edtEmail, edtPhone;
    private RadioButton radioMale;
    private Button btnSignup;
    private Spinner list_countryCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        Mapping(v);
        Event();
        return v;
    }

    public void Mapping(View view) {
        edtFullName = view.findViewById(R.id.fullname);
        edtAcc = view.findViewById(R.id.account);
        edtPass = view.findViewById(R.id.pswd);
        edtAddr = view.findViewById(R.id.address);
        edtEmail = view.findViewById(R.id.mal);
        edtPhone = view.findViewById(R.id.phone);
        radioMale = view.findViewById(R.id.radioMale);
        btnSignup = view.findViewById(R.id.act);
        list_countryCode = view.findViewById(R.id.list_code);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_dropdown_item, Country.code);
        list_countryCode.setAdapter(adapter);
    }

    public void Event() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = edtFullName.getText().toString().trim();
                String account = edtAcc.getText().toString().trim();
                String password = edtPass.getText().toString().trim();//Hash SHA1
                String address = edtAddr.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                int code = list_countryCode.getSelectedItemPosition();
                String phone = edtPhone.getText().toString().trim();
                boolean sex = radioMale.isChecked();
                ThongTinNguoiDung tt = new ThongTinNguoiDung(fullName, account, password, address, email, code, phone, sex);
                if (validate(tt.getFullName(), tt.getAcc(), tt.getPass(), tt.getAddress(), tt.getEmail(), tt.getPhoneNumber())) {
                    final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setContentView(R.layout.layout_loading);
                    dialog.show();
                    tt.setPass(getSHA1Hash(tt.getPass()));
                    checkExistAcc(dialog, tt);
                }
            }

        });

    }

    private boolean validate(String fullName, String account, String password, String address, String email, String phone) {
        boolean done = true;
        if (fullName.isEmpty() || account.isEmpty() || password.isEmpty() || address.isEmpty() || email.isEmpty()) {
            done = false;
            Toast.makeText(getActivity(), "Xin vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            String validateName = "[\\p{L}\\s]+";
            String validateAccount = "^[a-z0-9._-]{3,15}$";
            String validatePassword = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
            String validateEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            String validatePhone = "\\d{9,12}$";
            if (!(fullName.matches(validateName))) {
                edtFullName.setError("Tên không hợp lệ");
                done = false;
            }
            if (!(account.matches(validateAccount))) {
                edtAcc.setError("Tài khoản không hợp lệ");
                done = false;
            }
            if (!(password.matches(validatePassword))) {
                edtPass.setError("Mật khẩu phải có ít nhất 8 kí tự, phải có kí tự hoa, thường và kí tự đặc biệt ");
                done = false;
            }
            if (!(email.matches(validateEmail))) {
                edtEmail.setError("Email không hợp lệ");
                done = false;
            }
            if (!(phone.matches(validatePhone))) {
                edtPhone.setError("Số điện thoại không hợp lệ");
                done = false;
            }
        }
        return done;
    }

    private void mSignUp(final Dialog dialog, ThongTinNguoiDung tt) {
        int sex = tt.getSex() ? 1 : 0;
        RetrofitClient.getInstace().getService()
                .createUser(tt.getFullName(), tt.getAcc(), tt.getPass(), tt.getEmail(), tt.getAddress(), tt.getID_countryCode(), tt.getPhoneNumber(), sex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("success")) {
                            dialog.cancel();
                            Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                            fragLogin();
                        } else {
                            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
                    }
                });
//                .enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String result = response.body().string();
//                    if (result.equals("success")) {
//                        dialog.cancel();
//                        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
//                        fragLogin();
//                    } else {
//                        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private void checkExistAcc(final Dialog dialog, final ThongTinNguoiDung tt) {
        RetrofitClient.getInstace()
                .getService()
                .checkExist(tt.getAcc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("success")) mSignUp(dialog, tt);
                        else {
                            if (result.equals("exist")) {
                                Toast.makeText(getActivity(), "Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "Đã xảy ra lỗi. Vui lòng thử lại", Toast.LENGTH_LONG).show();
                            }
                            dialog.cancel();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

//                .enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String result = response.body().string();
//                    if (result.equals("success")) mSignUp(dialog, tt);
//                    else if (result.equals("exist")) {
//                        Toast.makeText(getActivity(), "Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getActivity(), "Đã xảy ra lỗi. Vui lòng thử lại", Toast.LENGTH_LONG).show();
//                    }
//                    dialog.cancel();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
//                dialog.cancel();
//            }
//        });
    }

    private void fragLogin() {
        ((LoginActivity) Objects.requireNonNull(getActivity())).getFragLogin();
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
