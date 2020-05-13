package com.hh.activitydemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Button mBtn1 =(Button) findViewById(R.id.Btn1);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity1.this,Activity2.class);
                startActivity(intent);
            }
        });
        Log.v("BootMode","Activity1 onCreate()");
        TextView mTv_main=(TextView)findViewById(R.id.tv1);
        mTv_main.setText("Activity1 task id:"+this.getTaskId());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("BootMode","Activity1 onDestroy()");
    }
}
