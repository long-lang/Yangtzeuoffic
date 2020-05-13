package com.example.bindservicedemo01;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
public class MyService extends Service {
    class MyBinder extends Binder {
        //创建服务的代理,调用服务中的方法
        public void methodInBinder() {
            Log.i("service", "执行MyBinder中的methodInBinder()方法");
            method();
        } }
    public void method() {
        Log.i("service", "执行MyService中的method()方法");
    }
    @Override
    public void onCreate() {
        Log.i("service", "创建服务，执行onCreate()方法");
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("service", "绑定服务，执行onBind()方法");
        return new MyBinder();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("service", "解绑服务，执行onUnbind()方法");
        return super.onUnbind(intent);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service","call onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        Log.i("service","call onDestroy...");
    } }