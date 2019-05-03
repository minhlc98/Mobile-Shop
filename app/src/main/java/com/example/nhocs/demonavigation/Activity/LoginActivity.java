package com.example.nhocs.demonavigation.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhocs.demonavigation.Adapter.LoginAdapter;
import com.example.nhocs.demonavigation.Fragment.FragmentLogin;
import com.example.nhocs.demonavigation.Fragment.Fragment_SignUp;
import com.example.nhocs.demonavigation.R;

public class LoginActivity extends AppCompatActivity {

    TextView tvSignIn,tvSignUp;
    //FrameLayout layout_SignIn_SignUp;
    ViewPager vp_Login;

    private FragmentManager fragmentManager=getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Mapping();
    }
    public void Mapping(){
        vp_Login=findViewById(R.id.vp_Login);
        vp_Login.setAdapter(new LoginAdapter(getSupportFragmentManager()));
        TabLayout tabLayout=findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp_Login);
    }
    public void addFragment (int i){

        FragmentTransaction transaction=fragmentManager.beginTransaction();
        Fragment fragment=null;
        //Toast.makeText(this, String.valueOf(fragmentManager.getBackStackEntryCount()), Toast.LENGTH_SHORT).show();
        switch (i){
            case 1:
                fragment=new Fragment_SignUp();
                tvSignUp.setTextColor(getResources().getColor(R.color.green));
                tvSignIn.setTextColor(getResources().getColor(R.color.black));
                break;

            default://mặc định sẽ gọi fragment Login
                fragment=new FragmentLogin();
                tvSignIn.setTextColor(getResources().getColor(R.color.green));
                tvSignUp.setTextColor(getResources().getColor(R.color.black));
                break;
        }
       // transaction.replace(R.id.layout_SignIn_Up,fragment);
        transaction.commit();
    }
    public void getFragLogin(){
        vp_Login.setCurrentItem(0);
    }
}
