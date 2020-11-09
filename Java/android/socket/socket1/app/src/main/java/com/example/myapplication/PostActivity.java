package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import application.client.Client;
import application.message.MessageResult;
import application.message.order.MessageOrder;
import application.message.order.MessageOrderResult;


public class PostActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
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

            new Thread( () -> {
                try {
                    MessageOrderResult mor = (MessageOrderResult)MainActivity.client.sendMessage(
                            new MessageOrder(address.getText().toString(),order.getText().toString()));
                    if (!mor.checkError())
                        new AlertActivity(Client.sOrderNumber+ mor.getNumber()).show(getSupportFragmentManager(),"Error");
                    else
                        new AlertActivity(Client.sResponseError).show(getSupportFragmentManager(),"Error");
                }
                catch (Exception e) {}
            }).start();


            address.setText("");
            order.setText("");


        } catch (Exception e){
            new IntentMessage(getApplicationContext(), e.getMessage()).run();
        }
    }

}