package com.example.bindservicedemo01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    private MyService.MyBinder myBinder;
    private MyConn myconn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void bindClick(View v){
        Log.i("service", "bindClick()");
        if (myconn == null) {
            myconn = new MyConn(); //创建连接服务的对象
        }
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, myconn, BIND_AUTO_CREATE); //绑定服务
    }
    public void callClick(View v){
        Log.i("service", "callClick()");
        myBinder.methodInBinder(); //调用服务中的方法
    }
    //第二个组件
    public void callClick1(View v){
        Log.i("service", "callClick1()");
        myBinder.methodInBinder(); //调用服务中的方法
    }
    public void unbindClick(View v){
        Log.i("service", "unbindClick()");
        if (myconn != null) {
            unbindService(myconn); //解绑服务
            myconn = null;
        } }
    private class MyConn implements ServiceConnection {//创建MyConn类,用于实现连接服务
        //当成功绑定服务时调用的方法,该方法获取MyService中的Ibinder对象
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (MyService.MyBinder) iBinder;
            Log.i("service", "服务成功绑定, 内存地址为:" + myBinder.toString());
        }
        //当服务失去连接时调用的方法
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("service", "服务成功解绑");
        } } }