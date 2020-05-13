package com.example.handlerdemo01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    final static int MESSAGE_WHAT=11;
    final static int MESSAGE_WHAT1=12;
    final static String TAG="Message";
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler= new Handler(){
//在主线程中创建一个Handler，然后重写该Handler的handlerMessage方法，
            //可以看到该方法传入了一个参数Message，
// 该参数就是我们从其他线程传递过来的信息
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MESSAGE_WHAT:
                        Log.d(TAG, "main thread receiver message: " + ((String)
                                msg.obj));
                        break;
                } }
        };
        sendMessageToMainThreadByWorkThread();
    }
    private void sendMessageToMainThreadByWorkThread() {
        new Thread(){
//子线程中传递信息的方式：
// 子线程通过Handler的obtainMessage()方法获取到一个Message实例，
//最后通过sendMessage将Message发送出去，其中Message有几个属性：
            // Message.what: 用来标识信息的int值，通过该值主线程能判断出来自不同地方的信息来源
// essage.arg1/Message.arg2: Message初始定义的用来传递int类型值的两个变量
            // Message.obj: 用来传递任何实例化对象
            @Override
            public void run() {
                Message message = mHandler.obtainMessage(MESSAGE_WHAT);
                message.obj = "I am message from work thread";
                mHandler.sendMessage(message);
                Message message1 = mHandler.obtainMessage(MESSAGE_WHAT1);
                message1.obj = "I am message from child thread";
                mHandler.sendMessage(message1);
            }
        }.start();
       receiveMessageFromChildThread();
    }

    private void receiveMessageFromChildThread() {
                mHandler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case MESSAGE_WHAT1:
                                Log.d(TAG, "child thread1 receiver message: " + ((String)
                                        msg.obj));
                                break;
                        }
                    }
                };
    }
}
