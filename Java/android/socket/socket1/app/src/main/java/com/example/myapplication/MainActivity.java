package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String defaultIP = "192.168.0.105";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            EditText ip = findViewById(R.id.ip_field);
            ip.setText(defaultIP);
        }
        catch (Exception e){
            String msg="";
            StackTraceElement[] s = e.getStackTrace();
            for (StackTraceElement i :s){
                msg+=i.toString()+'\n';
            }
            new IntentMessage(getApplicationContext(), msg).run();
        }
    }


    public void makeOrder(View view) {
        EditText ip = findViewById(R.id.ip_field);
        Intent i = new Intent(getApplicationContext(), PostActivity.class);
        i.putExtra("HOST",ip.getText().toString());
        i.putExtra("PORT", 8888);
        startActivity(i);
    }
}