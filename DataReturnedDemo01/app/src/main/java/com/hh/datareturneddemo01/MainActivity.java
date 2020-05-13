package com.hh.datareturneddemo01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("Boot","MainActivity onCreate()方法");
    }
    public void mainClick(View view){
        Log.v("Boot","mainClick 会调用startActivityForResult");
        Intent intent=new Intent(this, ChildActivity.class);
        startActivityForResult(intent,-1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, requestCode, data);
//        if(requestCode==1){
//            if(resultCode==1){
//                String string=data.getStringExtra("hello");
//                Log.v("Boot","获取的数据"+string);
//            }
//        }
        String string=data.getStringExtra("hello");
             Log.v("Boot","获取的数据"+string);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v("Boot","MainActivity onStart()方法");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v("Boot","MainActivity onResume()方法");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.v("Boot","MainActivity onRestart()方法");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v("Boot","MainActivity onStop()方法");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v("Boot","MainActivity onPause()方法");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v("Boot","MainActivity onDestroy()方法");
    }

}


