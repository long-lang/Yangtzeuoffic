package com.example.saveqqdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonSaveQQ {
    public static boolean saveUserInfo(Context context, User user) {
        //创建json对象
        JsonObject object = new JsonObject();
        //添加键值对
        String number=user.getNumber();
        String password=user.getPassword();
        object.addProperty("number", number);
        object.addProperty("password", password);
        String s=object.toString();
        BufferedWriter writer = null;
        String fileName="userInfo";
        File file = new File("E:\\"+ fileName + ".json");
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.v("json:","json:"+object.toString());
        return true;
    }

    public static  Map<String,String> getUserInfo(Context context) throws IOException {
       // SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String fileName  = "E:\\userInfo.json";
        Gson gson=new Gson();
        InputStream  is  = new FileInputStream("fileName");
        byte[] buffer=new byte[is.available()];
        is.read(buffer);
        String json=new String(buffer,"utf-8");
        User user=new Gson().fromJson(json,User.class);
        String number=user.getNumber();
        String password=user.getPassword();
        Map<String,String>userMap=new HashMap<String,String>();
        userMap.put("number",number);
        userMap.put("password",password);
        return userMap;
    }

}
