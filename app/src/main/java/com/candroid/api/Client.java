package com.candroid.api;


import android.util.Log;

import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class Client {

    private static final String TAG = Client.class.getSimpleName();

    private static final WebSocketConnection mConnection = new WebSocketConnection();

    public static void startSocketClient(){
        final String wsuri = "ws://3168035b.ngrok.com";

        try{
            mConnection.connect(wsuri, new WebSocket.ConnectionHandler() {
                @Override
                public void onOpen() {
                    Log.d(TAG, "Status: Connected to " + wsuri);
                    mConnection.sendTextMessage("Hello, world!");
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "Connection lost.");
                }

                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "Got echo: " + payload);
                }

                @Override
                public void onRawTextMessage(byte[] payload) {
                    Log.d(TAG, "Got echo: " + payload.toString());
                }

                @Override
                public void onBinaryMessage(byte[] payload) {
                    Log.d(TAG, "Got echo: " + payload.toString());
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }
}
