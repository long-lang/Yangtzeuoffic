package com.example.servicedemo01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("service", "创建服务，执行onCreate()方法");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service", "开启服务，执行onStartCommand()方法");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("service", "关闭服务，执行onDestroy()方法");
    } }
