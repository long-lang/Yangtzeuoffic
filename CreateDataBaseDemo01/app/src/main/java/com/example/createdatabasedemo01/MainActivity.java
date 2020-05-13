package com.example.createdatabasedemo01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDB();
    }

    private void createDB() {
//创建数据库并向info表中添加3条数据
        PersonDBOpenHelper helper = new PersonDBOpenHelper(this,"person.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        for (int i = 0; i < 3; i++) {
            ContentValues values = new ContentValues();
            values.put("name", "hh" + i);
            db.insert("info", null, values);
        }
        db.close();
    }


}
class PersonDBOpenHelper extends SQLiteOpenHelper{

    public PersonDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "person.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info (_id integer primary key autoincrement, name varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}