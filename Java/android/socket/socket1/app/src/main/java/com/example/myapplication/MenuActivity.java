package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import application.client.Client;
import application.message.menu.MessageMenu;
import application.message.menu.MessageMenuResult;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);

            MessageMenuResult mmr = (MessageMenuResult)MainActivity.client.sendMessage(new MessageMenu());
            if (!mmr.checkError()){
                TextView text = findViewById(R.id.menuText);
                String menu = "";
                for (String line: mmr.getOptions()){
                    menu+=line + "\n";
                }
                text.setText(menu);
            }
            else{
                new AlertActivity(Client.sResponseError + "\n Код ошибки: " + mmr.getResult()).show(getSupportFragmentManager(),"Error");
            }
        }
        catch (Exception e) {
            String msg = "";
            StackTraceElement[] s = e.getStackTrace();
            for (StackTraceElement i : s) {
                msg += i.toString() + '\n';
            }
            new AlertActivity(msg).show(getSupportFragmentManager(), "Error");
        }
    }
}
