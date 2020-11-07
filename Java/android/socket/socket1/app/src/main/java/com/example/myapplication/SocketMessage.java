package com.example.myapplication;
import android.os.StrictMode;

import androidx.fragment.app.FragmentManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SocketMessage {



    private String host;
    private int port;
    private FragmentManager fm;
    private static final String POST = "POST";

    public SocketMessage(FragmentManager fm, String host, int port) {
        this.host = host;
        this.port = port;
        this.fm = fm;
    }

    private void setThreadPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void sendMessage(String message) {

        new Thread(() -> {

            try(Socket clientSocket = new Socket(host, port);
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream())) {
                setThreadPolicy();

                dataOutputStream.writeUTF(POST);
                dataOutputStream.writeUTF(message);
                new AlertActivity(dataInputStream.readUTF()).show(fm, "Success");

            } catch (SocketException e) {
                new AlertActivity("Не удалось подключиться к серверу").show(fm, "Error");
            } catch (Exception e) {
                new AlertActivity(e.getMessage()).show(fm, "Error");
            }

        }).start();
    }

    public void receiveMessage() {

        new Thread(() -> {
            try {
                setThreadPolicy();
                Socket clientSocket = new Socket(host, port);
                DataInputStream dataOutputStream = new DataInputStream(clientSocket.getInputStream());
                new AlertActivity(dataOutputStream.readUTF()).show(fm,"Info");

            } catch (Exception e) {
                new AlertActivity(e.getMessage()).show(fm, "Error");
            }
        }).start();
    }
}