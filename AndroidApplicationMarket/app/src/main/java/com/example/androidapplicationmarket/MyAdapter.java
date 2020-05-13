package com.example.androidapplicationmarket;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends DefaultAdapter {

    public MyAdapter(String[] Info, Context context,String names[],int icons[]) {
        super(Info, context);
        this.names=names;
        this.icons=icons;
    }

    //需要适配的数据
    private String[] names ;
    //图片集合
    private int[] icons ;
//
//    public String[] getNames() {
//       return names;
//   }
//
//   public void setNames(String[] names) {
//        this.names = names;
//   }
//
//    public int[] getIcons() {
//        return icons;
//   }
//
//    public void setIcons(int[] icons) {
//        this.icons = icons;
//   }


    @Override
    public BaseHolder getHolder() {
        return new MyHolder();
    }
    private class MyHolder extends BaseHolder{
        private TextView tv;
        private ImageView iv;
        @Override
        public void initView() {
            view= View.inflate(context,R.layout.list_item,null);
            tv=(TextView)view.findViewById(R.id.item_tv);
            iv=(ImageView)view.findViewById(R.id.item_image);
            view.setTag(this);
        }

        @Override
        public void initData(int position) {
            tv.setText(names[position]);
            iv.setBackgroundResource(icons[position]);

        }
    }
}
