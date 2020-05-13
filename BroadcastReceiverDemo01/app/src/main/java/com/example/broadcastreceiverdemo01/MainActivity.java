package com.example.broadcastreceiverdemo01;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    MyReceiver1 mMyReceiver1;
    MyReceiver2 mMyReceiver2;
    MyReceiver3 mMyReceiver3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter=new IntentFilter();
       intentFilter.addAction("Broadcast");

        mMyReceiver1 = new MyReceiver1();
        registerReceiver(mMyReceiver1,intentFilter);
        mMyReceiver2 = new MyReceiver2();
        registerReceiver(mMyReceiver2,intentFilter);
        mMyReceiver3 = new MyReceiver3();
        registerReceiver(mMyReceiver3,intentFilter);
    }



    public void send(View view){
        Intent intent = new Intent();
        // 定义广播的事件类型
        intent.setAction("Broadcast");
//        intent.setComponent(new ComponentName("com.example.broadcastreceiverdemo01",
//                "com.example.broadcastreceiverdemo01.MyReceiver1"));
//        intent.setComponent(new ComponentName("com.example.broadcastreceiverdemo01",
//                "com.example.broadcastreceiverdemo01.MyReceiver2"));
//        intent.setComponent(new ComponentName("com.example.broadcastreceiverdemo01",
//                "com.example.broadcastreceiverdemo01.MyReceiver3"));
      //  intent.setPackage(" com.example.broadcastreceiverdemo01");

// 发送广播
        sendOrderedBroadcast(intent,null);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyReceiver1);
        unregisterReceiver(mMyReceiver2);
        unregisterReceiver(mMyReceiver3);
    }
}

