package com.hh.activitydemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Button mBtn2 = (Button) findViewById(R.id.Btn2);
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Log.v("BootMode", "Activity3 onCreate()");
        TextView mTv_main=(TextView)findViewById(R.id.tv3);
        mTv_main.setText("Activity3 task id:"+this.getTaskId());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("BootMode","Activity3 onDestroy()");
    }
}