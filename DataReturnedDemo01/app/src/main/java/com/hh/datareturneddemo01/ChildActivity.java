package com.hh.datareturneddemo01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ChildActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Log.v("Boot","ChildActivity onCreate()方法");
    }
    public void childClick(View view){
        Log.v("Boot","childClick 调用startActivityForResult()方法;");
        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra("hello","Hello MainActivity!");
        setResult(-1,intent);
        finish();
        //startActivityForResult(intent,1);
    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.v("Boot","ChildActivity onStart()方法");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v("Boot","ChildActivity onResume()方法");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.v("Boot","ChildActivity onRestart()方法");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v("Boot","ChildActivity onStop()方法");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v("Boot","ChildActivity onPause()方法");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v("Boot","ChildActivity onDestroy()方法");
    }

}


