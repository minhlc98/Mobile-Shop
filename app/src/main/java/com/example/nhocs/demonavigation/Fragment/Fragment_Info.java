package com.example.nhocs.demonavigation.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.request.RequestOptions;
import com.example.nhocs.demonavigation.Activity.General_Info_Activity;
import com.example.nhocs.demonavigation.Activity.MainActivity;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.Country;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.Model.ShrPreferences;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.R;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class Fragment_Info extends Fragment {
    private EditText edtName, edtAddr, edtEmail, edtPhone;
    private Spinner listCountryCode;
    private RadioButton radioMale, radioFemale;
    private ImageView imgProfile;
    private Button btnEditImg, btnChange, menu;
    private final int REQUEST_CODE = 1234;
    private String realpath;
    private boolean isChangeImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_user, container, false);
        Mapping(view);
        Event();
        getData();
        return view;
    }

    public void Mapping(View view) {
        edtName = view.findViewById(R.id.fullname_info);
        edtAddr = view.findViewById(R.id.address_info);
        edtEmail = view.findViewById(R.id.mal_info);
        edtPhone = view.findViewById(R.id.phone_info);
        btnEditImg = view.findViewById(R.id.btnEditImg);
        listCountryCode = view.findViewById(R.id.list_code);
        radioMale = view.findViewById(R.id.radioMale_info);
        radioFemale = view.findViewById(R.id.radioFemale_info);
        imgProfile = view.findViewById(R.id.imgProfile);
        listCountryCode = view.findViewById(R.id.list_code_info);
        btnChange = view.findViewById(R.id.change);
        menu = view.findViewById(R.id.menuPop);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_dropdown_item, Country.code);
        listCountryCode.setAdapter(adapter);
    }

    public void Event() {
        btnEditImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions( //Method of Fragment
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE
                    );
                } else {
                    openLibrary();
                }
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ThongTinNguoiDung tt = ShrPreferences.getInstance(getActivity()).getInfo();
                final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
                if (isChangeImage) {
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setContentView(R.layout.layout_loading);
                    dialog.show();
                    File file = new File(realpath);
                    String file_path = file.getAbsolutePath();
                    String[] arrName = file_path.split("\\.");
                    file_path = arrName[0] + System.currentTimeMillis() + "." + arrName[1];
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("upload_file", file_path, requestBody);
                    RetrofitClient.getInstace()
                            .getService()
                            .uploadPhoto(body)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<ResponseBody>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(ResponseBody newImg) {
                                    try {
                                        update_info(newImg.string(), tt.getImage(), dialog);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d("Main10",e.getMessage());
                                    Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
                                    dialog.cancel();
                                }
                            });
                } else {
                    update_info(tt.getImage(), "image-user/Blank-profile.png", dialog);
                }
            }

        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), menu);
                popupMenu.getMenuInflater().inflate(R.menu.setting, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.changePass) {
                            ((General_Info_Activity) Objects.requireNonNull(getActivity())).addFragmentChangePass();
                        } else {
                            MainActivity.isLogin = false;
                            ShrPreferences.getInstance(getActivity()).putInfo(null);
                            (Objects.requireNonNull(getActivity())).finish();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void getData() {
        ThongTinNguoiDung tt = ShrPreferences.getInstance(getActivity()).getInfo();
        String url = "http://minhlc.000webhostapp.com/" + tt.getImage();
        GlideApp.with(Objects.requireNonNull(getActivity())).load(url).apply(RequestOptions.circleCropTransform()).into(imgProfile);
        edtName.setText(tt.getFullName());
        edtAddr.setText(tt.getAddress());
        edtEmail.setText(tt.getEmail());
        edtPhone.setText(tt.getPhoneNumber());
        listCountryCode.setSelection(tt.getID_countryCode());

        if (tt.getSex()) radioMale.setChecked(true);
        else radioFemale.setChecked(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openLibrary();
            }
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            isChangeImage = true;
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri);
            GlideApp.with(Objects.requireNonNull(getActivity())).load(uri).apply(RequestOptions.circleCropTransform()).into(imgProfile);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openLibrary() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = Objects.requireNonNull(getActivity()).getContentResolver().query(contentUri, proj, null, null, null);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    private void update_info(final String newImage, String oldImage, final Dialog dialog) {
        final String name = edtName.getText().toString();
        final String address = edtAddr.getText().toString();
        final String email = edtEmail.getText().toString();
        final int position = listCountryCode.getSelectedItemPosition();
        final String phone = edtPhone.getText().toString();
        final int sex = radioMale.isChecked() ? 1 : 0;
        final ThongTinNguoiDung tt = ShrPreferences.getInstance(getActivity()).getInfo();
        RetrofitClient.getInstace().getService()
                .update_info(name, address, email, position, phone, sex, oldImage, newImage, tt.getID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody res) {
                        try {
                            String respone=res.string();
                            if (respone.equals("success")) {
                                tt.setImage(newImage);
                                tt.setFullName(name);
                                tt.setAddress(address);
                                tt.setEmail(email);
                                tt.setID_countryCode(position);
                                tt.setPhoneNumber(phone);
                                if (sex == 1) tt.setSex(true);
                                else tt.setSex(false);
                                ShrPreferences.getInstance(getActivity()).putInfo(tt);
                                isChangeImage = false;
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Main10", e.getMessage());
                        Toast.makeText(getActivity(), "Không có kết nối2", Toast.LENGTH_LONG).show();
                    }
                });

    }
}
