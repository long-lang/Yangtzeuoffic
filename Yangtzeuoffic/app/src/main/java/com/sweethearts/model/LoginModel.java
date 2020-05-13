package com.sweethearts.model;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sweethearts.R;
import com.sweethearts.Utils.MyUtils;
import com.sweethearts.contract.LoginContract;
import com.sweethearts.http.OkHttp;
import com.sweethearts.url.Url;
import com.sweethearts.view.Activity.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.LogRecord;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel implements LoginContract.LoginModel{
    private final static String language = "zh_CN";
    Handler handler;


    public void loadLoginEvent(final Activity activity, final LoginContract.LoginView view) {

        view.getLogin_btn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_number = Objects.requireNonNull(view.getUserName_ET().getText()).toString().trim();
                String user_password = Objects.requireNonNull(view.getPassword_ET().getText()).toString().trim();
                if (user_number.isEmpty()) {
                    ToastUtils.showShort(R.string.input_number);
                    return;
                }
                if (user_password.isEmpty()) {
                    ToastUtils.showShort(R.string.input_pass);
                    return;
                }
                ToastUtils.showLong(R.string.login_ing);

                doLogin(activity,user_number,user_password);
            }
        });

    }

    public void doLogin(final Activity activity,final String userName,final String password){
        LogUtils.i(userName,password);
        handler = new Handler(activity.getMainLooper()) ;
        Request request = new Request.Builder()
                .url(Url.Yangtzeu_Login_Path)
                .get()
                .build();
        OkHttp.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Intent intent = new Intent(activity, activity.getClass());
                activity.startActivity(intent);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                if (responseBody.contains("我的账户")) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                } else if (responseBody.contains("用户名") &&responseBody.contains("密码")) {
                    Document document = (Document) Jsoup.parse(responseBody);
                    Elements scripts = document.select("script");
                    String scripts_text = scripts.toString();
                    String regex0 = "CryptoJS.SHA1[(]\'(.*?)\'";
                    List<String> key_list = MyUtils.getSubUtil(scripts_text, regex0);
                    if (!ObjectUtils.isEmpty(key_list)) {
                        String login_key = key_list.get(0);
                        String login_encode_pass = EncryptUtils.encryptSHA1ToString(login_key + password).toLowerCase();
                        LogUtils.i("密码加密前缀：" + login_key, "密码加密完成：" + login_encode_pass);
                        login(activity,userName,login_encode_pass);
                    } else {
                        LogUtils.i("lel", "数据初始化失败");
                    }

                }
            }
        });
    }

    public void login(final Activity activity,final String userName,final String login_encode_pass){
        LogUtils.i(userName,login_encode_pass);
        FormBody formBody = new FormBody.Builder()
                .add("username", userName)
                .add("password", login_encode_pass)
                .add("encodedPassword", "")
                .add("session_locale", language)
                .build();

        Request request = new Request.Builder()
                .post(formBody)
                .url(Url.Yangtzeu_Login_Path)
                .build();
        OkHttp.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.i("lel","第二次请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String   myreponseBody = response.body().string();
                LogUtils.i("lel",myreponseBody);
                if (myreponseBody.contains("我的账户")) {
                    ToastUtils.showShort("登陆成功");
                    loginSuccess();
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);

                    return;
                }
                //登录失败的错误原因
                if (myreponseBody.contains("请不要过快点击")) {
                    LogUtils.i("点击过快");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            login(activity,userName, login_encode_pass);
                        }
                    }, 1000);
                    return;
                }

                if ( myreponseBody.contains("密码错误") ) {
                    ToastUtils.showShort("账号 或 密码错误");
                    //Intent intent = new Intent(activity, activity.getClass());
                    //activity.startActivity(intent);
                    return;
                }
            }
        });
    }
    private static void loginSuccess() {
        HashMap<String, String> cookieStr = new HashMap<>();
        List<Cookie> CookieList = OkHttp.cookieJar().loadForRequest(Objects.requireNonNull(HttpUrl.get(URI.create(Url.Yangtzeu_Login_Path))));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < CookieList.size(); i++) {
            builder.append(CookieList.get(i));
            builder.append(";");
        }

        //截取有用的Cookie
        String[] list = builder.toString().split(";");
        for (String string : list) {
            if (string.contains("JSESSIONID")) {
                cookieStr.put("JSESSIONID", string + ";");
            } else if (string.contains("GSESSIONID")) {
                cookieStr.put("GSESSIONID", string + ";");
            } else if (string.contains("adc-ck-jwxt_pools")) {
                cookieStr.put("pools", string + ";");
            }
        }

        String term_id = SPUtils.getInstance("user_info").getString("term_id", "89");
        cookieStr.put("semester", "semester.id=" + term_id + ";");

        String mCookie = cookieStr.get("JSESSIONID") +
                cookieStr.get("GSESSIONID") +
                cookieStr.get("semester") +
                cookieStr.get("pools");
        LogUtils.i("获取到的cookie",mCookie);
        SPUtils.getInstance("user_info").put("online", true);
        SPUtils.getInstance("user_info").put("cookie", mCookie);
        LogUtils.i("从spUtils获取到的cookie值",SPUtils.getInstance("user_info").getString("cookie"));
        String name = SPUtils.getInstance("user_info").getString("number");


    }


}
