package com.example.broadcastreceiverdemo01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("receiver","receiver1接收到了广播事件");
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
