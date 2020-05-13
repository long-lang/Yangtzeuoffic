package com.hh.activitydemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int count;
    private TextView tv1;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index",count);
    }
    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );
        count=0;
        Button btn1 =(Button) findViewById(R.id.btn1);
        tv1 = (TextView) findViewById(R.id.tv1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(null!=savedInstanceState){
                    count=savedInstanceState.getInt("index",0);
                    Log.v("MainActivity", "onCreate()中：count="+count);
                }

                tv1.setText(""+count);
            }
        });
        Button btn2 =(Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(null!=savedInstanceState){
                    count=savedInstanceState.getInt("index",0);
                    Log.v("MainActivity", "onCreate()中：count="+count);
                }
                count--;
                tv1.setText(""+count);
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.v("MainActivity","调用了onStart（）方法");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v("MainActivity","调用了onResume（）方法");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v("MainActivity","调用了onPause（）方法");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.v("MainActivity", "调用了onStop（）方法");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v("MainActivity","调用了onDestroy（）方法");
    }
    @Override protected void onRestart()
    {
        super.onRestart();
        Log.v("MainActivity", "调用了onRestart（）方法");
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.v("MainActivity", "调用了onConfigurationChanged（）方法");
    }

}
