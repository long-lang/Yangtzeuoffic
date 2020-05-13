package com.example.servicedemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startClick(View view) {
//开启服务
        Intent intent = new Intent(MainActivity.this,
                MyService.class);
        startService(intent);
    }
    public void stopClick(View view) {
//关闭服务
        Intent intent = new Intent(MainActivity.this,
                MyService.class);
        stopService(intent);
    }
}
