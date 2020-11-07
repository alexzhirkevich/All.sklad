package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ScrollView;

public class IntentMessage implements Runnable {

    class MessageActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            ScrollView scrollView = new ScrollView(this);
            Intent intent = getIntent();
            String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            TextView textView = new TextView(this);
            textView.setTextSize(20);
            textView.setText(message);
            textView.setPadding(20,0,20,20);
            textView.setTextColor(getResources().getColor(R.color.colorText));
            scrollView.addView(textView);
            setContentView(scrollView);
        }
    }

    Context context;
    String message;

    public IntentMessage(Context packageContext, String messsage){
        this.message = messsage;
        this.context = packageContext;
    }

    @Override
    public void run() {
        Intent intent = new Intent(context,MessageActivity.class);
        intent.putExtra("EXTRA_MESSAGE", message);
        new Activity().startActivity(intent);
    }
}

