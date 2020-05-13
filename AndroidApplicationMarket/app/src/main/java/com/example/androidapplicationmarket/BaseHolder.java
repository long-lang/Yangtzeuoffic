package com.example.androidapplicationmarket;

import android.view.View;

public abstract class BaseHolder<T> {
    protected View view=null;
    public BaseHolder(){
        initView();
    }
    public abstract void initView() ;

    public View getView() {
        return view;
    }

    public abstract void initData(int position) ;
}
