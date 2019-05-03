package com.example.nhocs.demonavigation.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import com.bumptech.glide.request.RequestOptions;
import com.example.nhocs.demonavigation.Activity.General_Info_Activity;
import com.example.nhocs.demonavigation.Activity.LoginActivity;
import com.example.nhocs.demonavigation.Activity.MainActivity;
import com.example.nhocs.demonavigation.Api.RetrofitClient;
import com.example.nhocs.demonavigation.Model.Country;
import com.example.nhocs.demonavigation.Model.GlideApp;
import com.example.nhocs.demonavigation.Model.ShrPreferences;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.R;

import java.io.File;
import java.io.IOException;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Info extends Fragment {
    EditText edtName, edtAddr, edtEmail, edtPhone;
    Spinner listCountryCode;
    RadioButton radioMale, radioFemale;
    ImageView imgProfile;
    Button btnEditImg, btnChange, menu;
    final int REQUEST_CODE=1234;
    String realpath;
    boolean isChangeImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_info_user,container,false);
       Mapping(view);
       Event();
       getData();
       return view;
    }
    public void Mapping(View view){
        edtName=(EditText) view.findViewById(R.id.fullname_info);
        edtAddr=(EditText) view.findViewById(R.id.address_info);
        edtEmail=(EditText) view.findViewById(R.id.mal_info);
        edtPhone=(EditText) view.findViewById(R.id.phone_info);
        btnEditImg=(Button) view.findViewById(R.id.btnEditImg);
        listCountryCode=(Spinner) view.findViewById(R.id.list_code);
        radioMale=(RadioButton) view.findViewById(R.id.radioMale_info);
        radioFemale=(RadioButton) view.findViewById(R.id.radioFemale_info);
        imgProfile=(ImageView) view.findViewById(R.id.imgProfile);
        listCountryCode=(Spinner) view.findViewById(R.id.list_code_info);
        btnChange=(Button) view.findViewById(R.id.change);
        menu=(Button) view.findViewById(R.id.menuPop);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item, Country.code);
        listCountryCode.setAdapter(adapter);
    }
    public void Event(){
        btnEditImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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
                final ThongTinNguoiDung tt=ShrPreferences.getInstance(getActivity()).getInfo();
                final Dialog dialog = new Dialog(getActivity());
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
                    RetrofitClient.getInstace().getService().uploadPhoto(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String newImg = response.body().string();

                                update_info(newImg, tt.getImage(),dialog);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    update_info(tt.getImage(), "image-user/Blank-profile.png",dialog);
                }
            }

        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getActivity(),menu);
                popupMenu.getMenuInflater().inflate(R.menu.setting, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.changePass:
                                ((General_Info_Activity)getActivity()).addFragmentChangePass();
                                break;
                             default:
                                 ShrPreferences.getInstance(getActivity()).putInfo(null);
                                 Intent intent=new Intent(getActivity(), MainActivity.class);
                                 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                 startActivity(intent);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    public void getData(){
        ThongTinNguoiDung tt= ShrPreferences.getInstance(getActivity()).getInfo();
        String url="http://minhlc.000webhostapp.com/"+tt.getImage();
        GlideApp.with(getActivity()).load(url).apply(RequestOptions.circleCropTransform()).into(imgProfile);
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
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){
                    openLibrary();
                }
                break;
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE&&resultCode==getActivity().RESULT_OK&&data!=null){
            isChangeImage=true;
            Uri uri=data.getData();
            realpath=getRealPathFromURI(uri);
            GlideApp.with(getActivity()).load(uri).apply(RequestOptions.circleCropTransform()).into(imgProfile);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void openLibrary(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CODE);
    }

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
    public void update_info(final String newImage, String oldImage, final Dialog dialog){
        final String name=edtName.getText().toString();
        final String address=edtAddr.getText().toString();
        final String email=edtEmail.getText().toString();
        final int position=listCountryCode.getSelectedItemPosition();
        final String phone=edtPhone.getText().toString();
        final int sex= radioMale.isChecked() ? 1 : 0;
        final ThongTinNguoiDung tt=ShrPreferences.getInstance(getActivity()).getInfo();
        RetrofitClient.getInstace().getService().update_info(name, address, email, position, phone, sex, oldImage, newImage, tt.getID()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    if (res.equals("success")){
                        tt.setImage(newImage);
                        tt.setFullName(name);
                        tt.setAddress(address);
                        tt.setEmail(email);
                        tt.setID_countryCode(position);
                        tt.setPhoneNumber(phone);
                        if (sex==1) tt.setSex(true);
                        else tt.setSex(false);
                        ShrPreferences.getInstance(getActivity()).putInfo(tt);
                        isChangeImage=false;
                        dialog.cancel();
                        Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getActivity(), "Không có kết nối", Toast.LENGTH_LONG).show();
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
}
