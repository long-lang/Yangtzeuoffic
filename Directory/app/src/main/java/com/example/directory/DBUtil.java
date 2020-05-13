package com.example.directory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUtil {
    private String name;
    private SQLiteDatabase db;
    private ContentValues values;
    private String phone;
    MyHelper myHelper;
    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    private String tablename;

    DirecInfo direcInfo=new DirecInfo();

    public DBUtil() {
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        name=direcInfo.getName();
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public  void setPhone(String phone) {
        phone=direcInfo.getPhone();
        this.phone = phone;
    }

    public  SQLiteDatabase getDb() {
        return db;
    }

    public  void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public  ContentValues getValues() {
        return values;
    }

    public void setValues(ContentValues values) {
        this.values = values;
    }
   //增加
    public void add(String name,String phone){
        values = new ContentValues();       // 创建ContentValues对象
        values.put("name", name);           // 将数据添加到ContentValues对象
        values.put("phone", phone);
        db.insert("information", null, values);
        db.close();
    }

    //修改
    public void update(String[]infos,String phoneInfo){
        db = myHelper.getWritableDatabase();
        values = new ContentValues();
        values.put("phone", phoneInfo);
        db.update(tablename, values, "name=?",
                infos); // 更新并得到行数
        db.close();
    }

    //删除
    public void delete(){
        db = myHelper.getWritableDatabase();
        db.delete(tablename, null, null);
        db.close();
    }

    //查询
    public Cursor query(String tablename) {
        db = myHelper.getReadableDatabase();
        return db.query(tablename, null, null, null, null,
                null, null);

    }


}
  class MyHelper extends SQLiteOpenHelper {
      public String getDbname() {
          return dbname;
      }

      public  void setDbname(String dbname) {
          this.dbname = dbname;
      }

      public String getSql() {
          return sql;
      }

      public void setSql(String sql) {
          this.sql = sql;
      }

      private static String dbname;
      private  String sql;
    public MyHelper(Context context) {
        super(context,dbname, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}