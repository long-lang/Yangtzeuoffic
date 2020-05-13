package com.hh.systemcalldemo01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("activity传数据测试","调用了onCreate（）方法");
        //浏览器
        Button button_Web=(Button)findViewById(R.id.btn_web);
        button_Web.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //设置动作
                intent.setAction("android.intent.action.VIEW");
                //设置网址
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                Log.v("activity传数据测试","点击了【btn.Web】");

            }
        });
        //地图
        Button button_Geo=(Button)findViewById(R.id.btn_Geo);
        button_Geo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //设置动作
                intent.setAction("android.intent.action.VIEW");
                //设置经纬度
                intent.setData(Uri.parse("geo:38.899533,-77.036476"));
                startActivity(intent);
                Log.v("activity传数据测试","点击了【btn_Geo】");

            }
        });
        //拨打电话页面
        Button button_DIAL=(Button)findViewById(R.id.btn_DIAL);
        button_DIAL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri telUri =Uri.parse("100861");
                Intent intent=new Intent(Intent.ACTION_DIAL,telUri);
                startActivity(intent);
                Log.v("activity传数据测试","点击了【btn.DIAL】");

            }
        });
        //直接拨打电话
        Button button_CALL=(Button)findViewById(R.id.btn_CALL);
        button_CALL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri telUri =Uri.parse("tel:100861");
                Intent intent=new Intent(Intent.ACTION_CALL,telUri);
                if(ActivityCompat.checkSelfPermission( this,Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                {
                    startActivity(intent);
                }
                Log.v("activity传数据测试","点击了【btn.CALL】");

            }
        });
        //卸载
        Button button_DELETE=(Button)findViewById(R.id.btn_DELETE);
        button_DELETE.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri uninstallUri=Uri.fromParts("package","com.hh.systemcalldemo01",null);
                Intent intent=new Intent(Intent.ACTION_DELETE,uninstallUri);;
                startActivity(intent);
                Log.v("activity传数据测试","点击了【btn.DELETE】");

            }
        });
        //安装
        Button button_ADDED=(Button)findViewById(R.id.btn_ADDED);
        button_ADDED.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri installUri=Uri.fromParts("package","com.hh.systemcalldemo01",null);
                Intent intent=new Intent(Intent.ACTION_PACKAGE_ADDED,installUri);
                startActivity(intent);
                Log.v("activity传数据测试","点击了【btn.ADDED】");

            }
        });
        //发邮件
        Button button_SENDTO=(Button)findViewById(R.id.btn_SENDTO);
        button_SENDTO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri emailUri=Uri.parse("mailto:1697946758@qq.com");
                Intent intent=new Intent(Intent.ACTION_SENDTO,emailUri);
                startActivity(intent);
                Log.v("activity传数据测试","点击了【btn.SENDTO】");
            }
        });
        //短信
        Button button_sms=(Button)findViewById(R.id.btn_sms);
        button_sms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.v("activity传数据测试","点击了【btn.SENDTO】");
               String phoneNumber="100861";
               String message="22222";
                Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto"+phoneNumber));
                intent.putExtra("sms_body",message);
                startActivity(intent);
                Log.v("activity传数据测试","点击了【btn.SENDTO】");

            }
        });
    }
}
