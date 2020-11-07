package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class PostActivity extends AppCompatActivity {


    private String host;
    private int port;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent intent = getIntent();
        host = intent.getStringExtra("HOST");
        port = Integer.parseInt(intent.getStringExtra("PORT")!=null?intent.getStringExtra("PORT"):"8888");
    }

    public void sendMessage(View view) {
        try {

            EditText address = findViewById(R.id.address);
            EditText order = findViewById(R.id.order);


            if (address.getText() == null || address.getText().length() == 0){
                new AlertActivity("Введите адрес").show(getSupportFragmentManager(),"Error");
                return;
            }

            if (order.getText() == null || order.getText().length() == 0){
                new AlertActivity("Введите заказ").show(getSupportFragmentManager(),"Error");
                return;
            }

            String data = address.getText() + "\n" + order.getText();

            SocketMessage s = new SocketMessage(getSupportFragmentManager(),host,port);

            s.sendMessage(data);

            address.setText("");
            order.setText("");


        } catch (Exception e){
            new IntentMessage(getApplicationContext(), e.getMessage()).run();
        }
    }

}