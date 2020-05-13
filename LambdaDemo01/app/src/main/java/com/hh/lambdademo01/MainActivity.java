package com.hh.lambdademo01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button  btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.layout);
     btn = (Button) findViewById(R.id.btn);
        //使用lambda后
        btn.setOnClickListener(v ->{
            btn.setText("我被点击了");
                }
        );

}
    }