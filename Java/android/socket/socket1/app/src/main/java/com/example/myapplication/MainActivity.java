package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import application.client.Client;
import application.message.MessageException;
import application.message.connection.MessageConnect;
import application.message.connection.MessageConnectResult;
import application.protocol.Config;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static Client client;
    public static boolean showExceptions = false;
    private static String host = Config.HOST;

    public void alertException(Exception e){
        String msg = "";
        StackTraceElement[] s = e.getStackTrace();
        for (StackTraceElement i : s) {
            msg += i.toString() + '\n';
        }
        new IntentMessage(getApplicationContext(), msg).run();
    }

    public void connect(View view) {
        Button b1 = findViewById(R.id.btnMenu);
        Button b2 = findViewById(R.id.btnMakeOrder);
        Button b3 = findViewById(R.id.btnConnect);
        ProgressBar pb = findViewById(R.id.progressBar);
        runOnUiThread(() -> {
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            pb.setVisibility(View.VISIBLE);
        });
        runOnUiThread(() -> host = ((EditText) findViewById(R.id.ip_field)).getText().toString());
        new Thread(() ->{
            try {
                client = new Client(host, Config.PORT);

                client.connect();
            } catch (Exception e) {
                new AlertActivity(Client.sConnectionFailed).show(getSupportFragmentManager(), "Error");
            }
            if (client != null &&
                    client.isConnected())
                runOnUiThread(() -> b3.setVisibility(View.INVISIBLE));
            else
                runOnUiThread(() -> b3.setVisibility(View.VISIBLE));
            runOnUiThread(() -> {
                pb.setVisibility(View.INVISIBLE);
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);
            });
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            EditText ip = findViewById(R.id.ip_field);
            ip.setText(Config.HOST);

            connect(null);


        } catch (Exception e) {
            if (showExceptions)
                alertException(e);
        }
    }
    public void makeOrder(View view) {
        if (!client.isConnected()){
            new AlertActivity("Отсутствует соединение с сервером").show(getSupportFragmentManager(), "Error");
            return;
        }
        try {
            Intent i = new Intent(getApplicationContext(), PostActivity.class);
            startActivity(i);
        } catch (Exception e) {
            if (showExceptions)
                alertException(e);
        }
    }

    public void getMenu(View view) {
        if (!client.isConnected()){
            new AlertActivity("Отсутствует соединение с сервером").show(getSupportFragmentManager(), "Error");
            return;
        }
        try {
            EditText ip = findViewById(R.id.ip_field);
            Intent i = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(i);
        } catch (Exception e) {
            if (showExceptions)
                alertException(e);
        }
    }
}