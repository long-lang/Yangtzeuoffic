package com.example.directory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    MyHelper myHelper;
    DBUtil dbUtil;
    private EditText mEtName;
    private EditText mEtPhone;
    private TextView mTvShow;
    private Button mBtnAdd;
    private Button mBtnQuery;
    private Button mBtnUpdate;
    private Button mBtnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new MyHelper(this);
        init();//初始化控件
    }
    private void init() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnAdd.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
         dbUtil=new DBUtil();
        String infos[]=new String [1000];
        String databasename="hh.db";
         String tablename="information";
         String sql="CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20),  phone VARCHAR(20))";
         myHelper.setDbname(databasename);
         myHelper.setSql(sql);
         dbUtil.setTablename(tablename);
         SQLiteDatabase db;
         ContentValues values;
         DirecInfo direcInfo=new DirecInfo();
        switch (v.getId()) {
            case R.id.btn_add: //添加数据
                String name = mEtName.getText().toString();
                String phone = mEtPhone.getText().toString();
                direcInfo.setName(name);
                direcInfo.setPhone(phone);

                //添加信息
                dbUtil.add(name,phone);
                Toast.makeText(this, "信息已添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query: //查询数据
                db = myHelper.getReadableDatabase();
                //调用工具类的查询
                Cursor cursor = dbUtil.query(tablename);
                if (cursor.getCount() == 0) {
                    mTvShow.setText("");
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                } else {
                    cursor.moveToFirst();
                    mTvShow.setText("Name :  " + cursor.getString(1) +
                            "  ；Tel :  " + cursor.getString(2));
                }
                while (cursor.moveToNext()) {
                    mTvShow.append("\n" + "Name :  " + cursor.getString(1) +
                            "  ；Tel :  " + cursor.getString(2));
                }
                cursor.close();
                db.close();
                break;
            case R.id.btn_update: //修改数据

                infos=new String[]{mEtName.getText().toString()};
                String phoneInfo=mEtPhone.getText().toString();
                // 要修改的数据
                 dbUtil.update(infos,phoneInfo);
                Toast.makeText(this, "信息已修改", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_delete: //删除数据
                dbUtil.delete();
                Toast.makeText(this, "信息已删除", Toast.LENGTH_SHORT).show();
                mTvShow.setText("");
                break;
        }
    }

}
