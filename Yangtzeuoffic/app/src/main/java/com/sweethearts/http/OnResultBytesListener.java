package com.sweethearts.http;


public interface OnResultBytesListener {
    void onResponse(byte[] bytes);
    void onFailure(String error);
}