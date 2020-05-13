package com.sweethearts.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sweethearts.R;
public class LaucherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laucher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startLoginActivity();
            }
        },2000);
    }

    private void startLoginActivity() {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        //关闭当前页面
        finish();
    }
}
