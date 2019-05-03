package com.example.nhocs.demonavigation.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.nhocs.demonavigation.Fragment.Fragment_ChangePass;
import com.example.nhocs.demonavigation.Fragment.Fragment_Info;
import com.example.nhocs.demonavigation.R;

public class General_Info_Activity extends AppCompatActivity {


    FragmentManager fragmentManager=getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__info_);

    }
    public void addFragmentChangePass() {
        Fragment fragment = new Fragment_ChangePass();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.layout_generalInfo, fragment, "ChangePass");
        transaction.commit();
    }
    public void removeFragmentChangePass(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment_ChangePass fragment= (Fragment_ChangePass) getFragmentManager().findFragmentByTag("ChangePass");
        transaction.remove(fragment);
        transaction.commit();
    }
}
