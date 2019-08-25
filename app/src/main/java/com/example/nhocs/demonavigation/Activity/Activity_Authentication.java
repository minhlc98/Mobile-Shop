package com.example.nhocs.demonavigation.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.R;
import com.example.nhocs.demonavigation.Ulti.NetworkManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Activity_Authentication extends AppCompatActivity {
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    String verificationID;
    String note = "Mã xác thực sẽ được gửi đến số điện thoại  trong vòng 1 phút";
    FirebaseAuth mAuth;
    EditText edtCode;
    Button btnXacThuc, btnGuiLai;
    ThongTinNguoiDung thongTin;
    TextView tvNote;
    public final String info = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__authentication);
        Mapping();
        initAuth();
        getData();
        event();
    }

    public void initAuth() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                //Hàm này được gọi trong 2 tình huống:
                //1: khi đã xác thực rồi thì sẽ gọi hàm này(Xảy ra khi số đt đó nằm trên thiết bị đó và đã xác thực trước đó, nếu số đt đó nằm ở thiết bị khác thì sẽ không gọi hàm này)
                //2: Tự động lấy mã code: trên một số thiết bị sẽ có hỗ trợ tự động lấy mã code mà không cần người dùng nhập vào


                //lấy mã code trong SMS
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    edtCode.setText(code);
                }
                signInWithCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Activity_Authentication.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verificationID = s;
                resendingToken = forceResendingToken;
                super.onCodeSent(s, forceResendingToken);
            }
        };
    }

    public void Mapping() {
        edtCode = findViewById(R.id.edtCode);
        btnXacThuc = findViewById(R.id.btnXacThuc);
        btnGuiLai = findViewById(R.id.btnGuiLai);
        mAuth = FirebaseAuth.getInstance();
        tvNote = findViewById(R.id.tvNote);
    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }

    private void verifyCode(String code) {
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationID, code);
        signInWithCredential(phoneAuthCredential);
    }

    private void signInWithCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //saveInfo();
                            Create_OrderID(thongTin.getFullName(), thongTin.getPhoneNumber(), thongTin.getAddress(), thongTin.getEmail());
                        } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            edtCode.setError("Người dùng đã tồn tại");
                        } else {
                            edtCode.setError("Mã xác thực không đúng");
                        }
                    }
                });
    }

    public void getData() {
        thongTin = (ThongTinNguoiDung) getIntent().getSerializableExtra(info);
        StringBuilder buffer = new StringBuilder(note);
        buffer.insert(42, thongTin.getPhoneNumber());
        tvNote.setText(buffer.toString());
        Log.d("Main10",thongTin.getPhoneNumber());
        sendVerificationCode(thongTin.getPhoneNumber());
    }

    public void Create_OrderID(final String TenKH, final String DienThoai, final String DiaChi, final String Email) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        RetrofitClient.getInstace().getService()
                .createOrderID(TenKH, DienThoai, DiaChi, Email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String id_donhang) {
                        if (!id_donhang.equals("fail")) Create_Order_Detail(id_donhang, dialog);
                        else {
                            dialog.cancel();
                            Toast.makeText(Activity_Authentication.this, "Kết nối thất bại", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Activity_Authentication.this, "Kết nối thất bại", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
    }

    public void Create_Order_Detail(final String id_donhang, final Dialog dialog) {
        RetrofitClient.getInstace().getService().createOrderDetail(createJSON(id_donhang))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String res) {
                        dialog.cancel();
                        if (res.equals("Success")) {
                            MainActivity.database.Query("Delete from GIOHANG");//Xóa dữ liệu trong database
                            MainActivity.soluong_giohang = 0;//chỉnh lại số 0 trên hình cart
                            Dialog dialog_success = new Dialog(Activity_Authentication.this);
                            dialog_success.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog_success.setCanceledOnTouchOutside(false);
                            dialog_success.setContentView(R.layout.pay_success);
                            TextView txtID =  dialog_success.findViewById(R.id.txtID);
                            txtID.append(String.valueOf(id_donhang));
                            ImageButton imageButton =  dialog_success.findViewById(R.id.image_close);
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Activity_Authentication.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            });
                            dialog_success.show();
                        } else {
                            Toast.makeText(Activity_Authentication.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Activity_Authentication.this, "Kết nối thất bại", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
    }

    public String createJSON(String id_donhang) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < GioHang.mang_gio_hang.size(); ++i) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("ID_DonHang", id_donhang);
                jsonObject.put("ID_SanPham", GioHang.mang_gio_hang.get(i).getID());
                jsonObject.put("Gia", GioHang.mang_gio_hang.get(i).getGia());
                jsonObject.put("SoLuong", GioHang.mang_gio_hang.get(i).getSoLuong());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    protected void onStart() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            FirebaseAuth.getInstance().signOut();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (MainActivity.soluong_giohang > 0) super.onBackPressed();
        else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this, mCallbacks, token);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void event() {
        btnXacThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkManager.isOnline(getApplicationContext())) {
                    String code = edtCode.getText().toString();
                    if (code.length() == 6) verifyCode(code);
                    else edtCode.setError("Mã xác thực không hợp lệ");
                } else {
                    Toast.makeText(Activity_Authentication.this, "Không có kết nối", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnGuiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendVerificationCode(thongTin.getPhoneNumber(), resendingToken);
            }
        });

        btnXacThuc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    btnXacThuc.setBackgroundResource(R.color.green);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    btnXacThuc.setBackgroundResource(R.color.blue);
                return false;
            }
        });
        btnGuiLai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    btnGuiLai.setBackgroundResource(R.color.green);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    btnGuiLai.setBackgroundResource(R.color.blue);
                return false;
            }
        });
    }
}
