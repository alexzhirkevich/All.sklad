package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import application.client.Client;
import application.message.MessageException;
import application.message.connection.MessageConnect;
import application.protocol.Config;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static Client client;
    public static boolean showExceptions = false;
    private static boolean connected = false;
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
        runOnUiThread(() -> b1.setEnabled(false));
        runOnUiThread(() -> b2.setEnabled(false));
        try {
            runOnUiThread(() -> host = ((EditText) findViewById(R.id.ip_field)).getText().toString());

            client = new Client(host, Config.PORT);

            if (client.connect().checkError())
                new AlertActivity(Client.sConnectionFailed).show(getSupportFragmentManager(), "Error");
            connected = true;
            runOnUiThread(() -> findViewById(R.id.btnConnect).setVisibility(View.INVISIBLE));
        } catch (Exception e) {
            new AlertActivity(Client.sConnectionFailed).show(getSupportFragmentManager(), "Error");
            runOnUiThread(() -> findViewById(R.id.btnConnect).setVisibility(View.VISIBLE));
        }
        runOnUiThread(() -> findViewById(R.id.progressBar).setVisibility(View.INVISIBLE));
        runOnUiThread(() -> b1.setEnabled(true));
        runOnUiThread(() -> b2.setEnabled(true));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            EditText ip = findViewById(R.id.ip_field);
            ip.setText(Config.HOST);

            new Thread(() ->connect(null)).start();


        } catch (Exception e) {
            if (showExceptions)
                alertException(e);
        }
    }
    public void makeOrder(View view) {
        if (!connected){
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
        if (!connected){
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