package com.hh.texthtmlbdemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.layout);
        TextView textView=(TextView)findViewById(R.id.linktext) ;
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
