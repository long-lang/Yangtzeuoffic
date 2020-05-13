package com.sweethearts.view.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;


import com.blankj.utilcode.util.SPUtils;
import com.sweethearts.R;
import com.sweethearts.Utils.MyUtils;
import com.sweethearts.http.OkHttp;
import com.sweethearts.url.Url;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.Cookie;
import okhttp3.FormBody;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends FragmentActivity {
    private final static String language = "zh_CN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}

