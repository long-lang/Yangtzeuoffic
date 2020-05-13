package com.example.androidapplicationmarket;
/**
 * 在加载Item布局时，会使用findViewById()方法找到Item布局中的
 * 各个控件，在每一次加载新的Item数据时都会进行控件寻找，这
 * 样也会产生耗时操作。
 * – 为了进一步的优化ListView减少耗时操作，可以将要加载的子
 * View放在ViewHolder类中，当第一次创建convertView时将这些
 * 控件找出，在第二次重用convertView时就可直接通过
 * convertView中的getTag()方法获得这些控件。
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public abstract class DefaultAdapter<T> extends BaseAdapter {
    private String Info[]=null;
    Context context;
    public DefaultAdapter(String[] Info, Context context) {
        this.Info = Info;
        this.context = context;
    }

    public DefaultAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return Info.length;
    }

    @Override
    public Object getItem(int position) {
        return Info[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public abstract BaseHolder<T>getHolder();
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       BaseHolder<T>holder=null;
        if (convertView == null) {
           holder=getHolder();
           //holder.initView();
           convertView=holder.getView();
        }
        holder=(BaseHolder<T>)convertView.getTag();
        holder.initData(position);
        return convertView;
    }
}
