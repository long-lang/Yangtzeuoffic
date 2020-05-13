package com.example.timerdemo01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tvTime;
    private Timer timer;
//    private Handler mHandler;
    private  Calendar mCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new Timer();//创建timer对象
        tvTime=findViewById(R.id.tvTime);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.v("Timer","run()...");
                mCalendar = Calendar.getInstance();
                int hour = mCalendar.get(Calendar.HOUR_OF_DAY);//HOUR    进制为12小时   HOUR_OF_DAY  为24小时
                int minute = mCalendar.get(Calendar.MINUTE);//分钟
                int second = mCalendar.get(Calendar.SECOND) + 1;//秒数
                if (second == 60) {
                    minute += 1;
                    second = 0;
                }
                if (minute == 60){
                    hour += 1;
                    minute = 0;
                }
                if (hour == 12){
                    hour = 0;
                }
                String time = String.format("%d:%02d:%02d", hour, minute, second);
                mCalendar.set(Calendar.SECOND, second);
                mCalendar.set(Calendar.MINUTE, minute);
                mCalendar.set(Calendar.HOUR_OF_DAY, hour);

                Message message=new Message();
                message.what=0;
                message.obj=time;
                mHandler.sendMessage(message);

            }
        },0,1000);
    }
   Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.v("Timer","handleMessage()..");
            super.handleMessage(msg);
            String str=(String)msg.obj;
            tvTime.setText(str);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();//关闭timer
        }
    }
}
