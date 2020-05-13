package com.example.contentproviderdemo01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioRecord.SUCCESS;

public class ContentReslover extends AppCompatActivity {
    //定义一个uri路径的匹配器，如果路径匹配不成功返回 -1
    private static UriMatcher mUriMatcher = new UriMatcher(-1);
    //匹配路径成功时的返回码
    private static final int SUCCESS = 1;
    //数据库操作类的对象
    //private PersonDBOpenHelper helper;
    //添加路径匹配器的规则
    static {
        mUriMatcher.addURI("com.example.createdatabasedemo01", "info", SUCCESS);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_reslover);
        //第一步
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            //第二步， 第三个参数传送唯一值即可
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    1);
        } else {
            //取得权限后的业务逻辑
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //第三步
        switch (requestCode){
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //处理取得权限和后的业务逻辑
                } else {
                    //未取得权限的业务逻辑
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default :
        }
    }
    //读取其他程序数据的权限
    private void readContact(){
        Cursor cursor = null;
        //第二、第三步
        cursor = getContentResolver().query(Uri.parse("com.example.createdatabasedemo01/info"),
                null, null, null, null);
        //第四步

    }


}


