package com.candroid.api;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.candroid.app.BuildConfig;
import com.candroid.app.activities.MainActivity;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.dto.commands.Command;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class Client {

    private static final String TAG = Client.class.getSimpleName();

    private static final WebSocketConnection mConnection = new WebSocketConnection();

    private Activity activity;
    private Gson gson;
    private ProgressDialog progressDialog;

    public Client(Activity activity) {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Command.class, new CommandDeserializer())
                .create();
        this.activity = activity;
    }

    public void startSocketClient() {
        final String wsuri = BuildConfig.WS_URI;
        try {
            mConnection.connect(wsuri, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    Log.d(TAG, "Status: Connected to " + wsuri);
                    progressDialog = ProgressDialog.show(activity, "Connecting", "Connecting to Server...", true);
                    if (!BuildConfig.DEBUG) {
                        mConnection.sendTextMessage("connect");
                    } else {
                        mConnection.sendTextMessage("connect");
                    }
                }

                @Override
                public void onTextMessage(String payload) {
                    if (payload.contains("working_set")) {
                        ObjectPool objectPool = gson.fromJson(payload, ObjectPool.class);
                        ((MainActivity) activity).initDisplay(objectPool);
                    }
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    Log.d(TAG, "Got echo: " + payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "Connection lost.");
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                            .setTitle("Error")
                            .setMessage("Cannot establish a connection.")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            });
        } catch (WebSocketException e) {
            Log.d(TAG, e.toString());
        }
    }
}
