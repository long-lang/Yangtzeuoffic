package com.sweethearts.http.callback;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class StringCallBack implements Callback {
    private Handler UI = new Handler(Looper.getMainLooper());

    /**
     * 有网加载成功 onFinish 返回网络响应
     * 有网加载失败或没网情况下，本地有缓存 onFinish 返回缓存响应
     * 有网加载失败或没网情况下，本地无缓存 onFinish 返回错误信息
     *
     * @param call            请求
     * @param response        响应
     * @param isResponseExist 响应是否存在
     * @param isCacheResponse 响应存在的话，是否是缓存响应
     */
    public abstract void onFinish(Call call, String response, boolean isResponseExist, boolean isCacheResponse);

    @Override
    public void onFailure(@NonNull final Call call, @NonNull final IOException e) {

    }

    @Override
    public void onResponse(@NonNull final Call call, @NonNull Response response) throws IOException {


    }
}














