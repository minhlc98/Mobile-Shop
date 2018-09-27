package com.example.nhocs.demonavigation.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.example.nhocs.demonavigation.R;
public class StartMain extends AppCompatActivity {

    int remaining=3000;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_main);
    }
    public void setTime_Remaining() {
         timer=new CountDownTimer(remaining,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remaining-=1000;
            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(StartMain.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
    @Override
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
