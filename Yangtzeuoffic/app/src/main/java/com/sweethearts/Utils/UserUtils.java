package com.sweethearts.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.sweethearts.http.OkHttp;
import com.sweethearts.http.OnResultStringListener;
import com.sweethearts.url.Url;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class UserUtils {
    private final static String language = "zh_CN";
    private static Handler handler;

    public static void do_Login(final Context context, String[] userInfo, final OnLogResultListener logResultListener) {
        if (userInfo.length != 2 || context == null) {
            if (logResultListener != null)
                logResultListener.onFailure("登录信息错误");
            return;
        }
        handler = new Handler(context.getMainLooper());
        final String userNumber = userInfo[0];
        final String userPassword = userInfo[1];
        OkHttp.do_Get(Url.Yangtzeu_Login_Path, new OnResultStringListener() {
            @Override
            public void onResponse(String response) {
                if (response.contains("我的账户")) {
                    if (logResultListener != null)
                        logResultListener.onSuccess(response);
                    loginSuccess();
                } else if (response.contains("用户名") && response.contains("密码")) {
                    Document document = Jsoup.parse(response);
                    Elements scripts = document.select("script");
                    String scripts_text = scripts.toString();

                    String regex0 = "CryptoJS.SHA1[(]\'(.*?)\'";
                    List<String> key_list = MyUtils.getSubUtil(scripts_text, regex0);
                    if (!ObjectUtils.isEmpty(key_list)) {
                        String login_key = key_list.get(0);
                        String login_encode_pass = EncryptUtils.encryptSHA1ToString(login_key + userPassword).toLowerCase();
                        LogUtils.i("密码加密前缀：" + login_key, "密码加密完成：" + login_encode_pass);
                        login(userNumber, login_encode_pass, logResultListener);
                    } else {
                        LogUtils.e(scripts_text);
                        logResultListener.onFailure("初始化登录参数错误，请等待软件更新");
                    }
                } else {
                    LogUtils.e(response);
                    logResultListener.onFailure(response);
                }
            }

            @Override
            public void onFailure(String error) {
                LogUtils.e("失败了");
            }
        });
    }
    private static void login(final String number, final String e_code_pass, final OnLogResultListener onLoginResultListener) {
        FormBody formBody = new FormBody.Builder()
                .add("username", number)
                .add("password", e_code_pass)
                .add("encodedPassword", "")
                .add("session_locale", language)
                .build();

        Request request = new Request.Builder()
                .post(formBody)
                .url(Url.Yangtzeu_Login_Path)
                .build();

        OkHttp.do_Post(request, new OnResultStringListener() {
            @Override
            public void onResponse(String result) {
                //登录成功
                if (result.contains("我的账户")) {
                    if (onLoginResultListener != null)
                        onLoginResultListener.onSuccess(result);
                    loginSuccess();
                    return;
                }
                //登录失败的错误原因
                if (result.contains("请不要过快点击")) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            login(number, e_code_pass, onLoginResultListener);
                        }
                    }, 1000);
                    return;
                }

                MyUtils.mVibrator(ActivityUtils.getTopActivity(), 200);
                //清除登录失败的cookie
                OkHttp.cookieJar().clear();
                OkHttp.cookieJar().clearSession();

                if (result.contains("密码错误")) {
                    if (onLoginResultListener != null)
                        onLoginResultListener.onFailure("密码错误");
                } else if (result.contains("账户不存在")) {
                    if (onLoginResultListener != null)
                        onLoginResultListener.onFailure("账户不存在");
                } else if (result.contains("验证码不正确")) {
                    if (onLoginResultListener != null)
                        onLoginResultListener.onFailure("验证码不正确");
                } else {
                    LogUtils.e(result);
                    if (onLoginResultListener != null)
                        onLoginResultListener.onFailure("登录未知错误");
                }
            }

            @Override
            public void onFailure(String error) {
                LogUtils.e(error);
                if (onLoginResultListener != null)
                    onLoginResultListener.onFailure("教务处网络过载，请稍后再试");
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

        String term_id = SPUtils.getInstance("user_info").getString("term_id", Url.Default_Term);
        cookieStr.put("semester", "semester.id=" + term_id + ";");

        String mCookie = cookieStr.get("JSESSIONID") +
                cookieStr.get("GSESSIONID") +
                cookieStr.get("semester") +
                cookieStr.get("pools");
        SPUtils.getInstance("user_info").put("online", true);
        SPUtils.getInstance("user_info").put("cookie", mCookie);
        String name = SPUtils.getInstance("user_info").getString("number");
    }

    public interface OnLogResultListener {
        void onSuccess(String response);
        void onFailure(String error);
    }
}
