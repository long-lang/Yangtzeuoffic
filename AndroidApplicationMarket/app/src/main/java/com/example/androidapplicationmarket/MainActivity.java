package com.example.androidapplicationmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    //需要适配的数据
    private String[] names = {"京东商城", "QQ", "QQ斗地主", "新浪微博", "天猫",
            "UC浏览器", "微信"};
    //图片集合
    private int[] icons = {R.drawable.jd, R.drawable.qq, R.drawable.dz,
            R.drawable.xl, R.drawable.tm, R.drawable.uc,R.drawable.wx};
    //MyAdapter myAdapter=new MyAdapter(names,icons);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化ListView控件
        mListView = (ListView) findViewById(R.id.lv);
        //创建一个Adapter的实例
      MyAdapter myAdapter = new MyAdapter(names,this,names,icons);
        //设置Adapter
        mListView.setAdapter(myAdapter);
    }
}
