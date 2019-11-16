package com.example.nhocs.demonavigation.Activity;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhocs.demonavigation.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class ThongTin extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar toolbarThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
        toolbarThongTin = findViewById(R.id.toolbarThongTin);
        setSupportActionBar(toolbarThongTin);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarThongTin.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarThongTin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng TGDD1 = new LatLng(10.753117, 106.661380);
        LatLng TGDD2 = new LatLng(10.778328, 106.656335);
        googleMap.addMarker(new MarkerOptions().position(TGDD1).title("708 Nguyễn Trãi"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(TGDD1));
        googleMap.addMarker(new MarkerOptions().position(TGDD2).title("270B Lý Thường Kiệt"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(TGDD2));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(11.5f));
    }
}
