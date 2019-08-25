package com.example.nhocs.demonavigation.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.nhocs.demonavigation.Adapter.LoginAdapter;
import com.example.nhocs.demonavigation.Fragment.FragmentLogin;
import com.example.nhocs.demonavigation.Fragment.Fragment_SignUp;
import com.example.nhocs.demonavigation.R;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TextView tvSignIn, tvSignUp;
    //FrameLayout layout_SignIn_SignUp;
    ViewPager vp_Login;

    private FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Mapping();
    }

    public void Mapping() {
        vp_Login = findViewById(R.id.vp_Login);
        vp_Login.setAdapter(new LoginAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp_Login);
    }

    public void getFragLogin() {
        vp_Login.setCurrentItem(0);
    }
}
