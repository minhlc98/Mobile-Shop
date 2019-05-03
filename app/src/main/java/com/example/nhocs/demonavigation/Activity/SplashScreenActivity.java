package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    CountDownTimer timer;
    int remaining=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    public void setTime_Remaining() {
        timer=new CountDownTimer(remaining,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remaining-=1000;
            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }
    @Override
    protected void onResume() {
        setTime_Remaining();
        super.onResume();
    }
}
