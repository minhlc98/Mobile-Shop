package com.example.nhocs.demonavigation.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.nhocs.demonavigation.Fragment.FragmentLogin;
import com.example.nhocs.demonavigation.Fragment.Fragment_SignUp;

public class LoginAdapter extends FragmentStatePagerAdapter {
    public String listTab[]={"Đăng nhập", "Đăng ký"};
    public FragmentLogin fragmentLogin;
    public Fragment_SignUp fragmentSignUp;
    public LoginAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: fragmentLogin=new FragmentLogin();return fragmentLogin;
            case 1: fragmentSignUp=new Fragment_SignUp();return fragmentSignUp;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
