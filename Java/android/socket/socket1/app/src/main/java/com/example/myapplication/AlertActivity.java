package com.example.myapplication;
import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

public class AlertActivity extends DialogFragment {

    private String message;

    public AlertActivity(String message){
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message).setPositiveButton("Ок", (dialog, id) -> dialog.cancel());
        return builder.create();
    }
}
