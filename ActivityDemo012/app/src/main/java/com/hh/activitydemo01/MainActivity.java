package com.hh.activitydemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button mBtn1 =(Button) findViewById(R.id.Main_Btn);
            mBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,Activity1.class);
                    startActivity(intent);
                }
            });
            Log.v("BootMode","MainActivity onCreate()");
            TextView mTv_main=(TextView)findViewById(R.id.Main_tv);
            mTv_main.setText("MainActivity task id:"+this.getTaskId());
        }
}
