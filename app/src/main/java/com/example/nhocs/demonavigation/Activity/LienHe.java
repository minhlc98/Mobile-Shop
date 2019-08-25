package com.example.nhocs.demonavigation.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhocs.demonavigation.R;

import java.util.Objects;

public class LienHe extends AppCompatActivity {

    Toolbar toolbarLienHe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        toolbarLienHe = findViewById(R.id.toolbarLienHe);
        setSupportActionBar(toolbarLienHe);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarLienHe.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarLienHe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
