package com.example.saveqqdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Map<String,String>userInfo=SPSaveQQ.getUserInfo(this);
//       boolean isSaveSuccess=SPSaveQQ.getUserInfo(this,number,password);
        //初始化界面
        initView();
       // Map<String,String>userInfo=SPSaveQQ.getUserInfo(this);//SharePreference
        Map<String,String>userInfo= null;
        try {
            userInfo = JsonSaveQQ.getUserInfo(this);//Gson
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userInfo!=null){
            etNumber.setText(userInfo.get("number"));
            etPassword.setText(userInfo.get("password"));
        }
    }

    private void initView() {
        etNumber=(EditText)findViewById(R.id.et_number);
        etPassword=(EditText)findViewById(R.id.et_password);
        btnLogin=(Button)findViewById(R.id.btn_login);
        //设置单击事件
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //单击登录时获取QQ账号和密码
        User user =new User();
        String number=etNumber.getText().toString().trim();
        String password=etPassword.getText().toString();
        user.setNumber(number);
        user.setPassword(password);

        //检验账号密码是否正确
        if(TextUtils.isEmpty(number)){
            Toast.makeText(this,"请输入QQ账号",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码:",Toast.LENGTH_SHORT).show();
            return;
        }
        //登陆成功
        Toast.makeText(this,"登陆成功",Toast.LENGTH_LONG).show();
        //保存用户信息
       // boolean isSaveSuccess=SPSaveQQ.saveUserInfo(this,number,password);
        boolean isSaveSuccess=JsonSaveQQ.saveUserInfo(this,user);
        if(isSaveSuccess){
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
        }

    }
}
