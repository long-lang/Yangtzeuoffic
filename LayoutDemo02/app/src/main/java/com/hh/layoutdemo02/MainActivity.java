package com.hh.layoutdemo02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.layout);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new MyClickListener());
    }
    class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            btn.setText("我被点击了");
        }
}
}