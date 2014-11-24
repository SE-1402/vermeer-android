package com.candroid.api;


import android.app.Activity;
import android.util.Log;

import com.candroid.app.BuildConfig;
import com.candroid.app.activities.MainActivity;
import com.candroid.app.dto.ObjectPool;
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

    public Client (Activity activity){
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        this.activity = activity;
    }

    public void startSocketClient(){
        final String wsuri = BuildConfig.WS_URI;
        try {
            mConnection.connect(wsuri, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    Log.d(TAG, "Status: Connected to " + wsuri);
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
                    Log.d(TAG, "Got echo: " + payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "Connection lost.");
                }
            });
        } catch (WebSocketException e) {
            Log.d(TAG, e.toString());
        }
    }
}
