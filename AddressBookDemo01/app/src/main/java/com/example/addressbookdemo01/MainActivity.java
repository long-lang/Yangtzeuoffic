package com.example.addressbookdemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取内容解析者
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri datauri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = resolver.query(uri, new String[]{"_id"}, null, null, null);
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            //System.out.println("Id:"+id);
            Log.v("info","联系人id: "+id);
            //查询data表
            Cursor datacursor = resolver.query(datauri, new String[]{"data1","mimetype"}, "raw_contact_id=?", new String[]{id}, null);
            while(datacursor.moveToNext()){
                String data1 = datacursor.getString(0);
               // System.out.println("data1:"+data1);
                Log.v("info","数据："+data1);
               // String mimetype = datacursor.getString(1);
                //
                //System.out.println("mimetype:"+mimetype);
            }
            datacursor.close();
           // System.out.println("------------");
            Log.v("info","--------------------");
        }
        cursor.close();

    }
}
