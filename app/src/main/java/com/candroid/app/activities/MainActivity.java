package com.candroid.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.candroid.app.R;
import com.candroid.app.dto.Macro;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.util.XMLPullParserHandler;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.crashlytics.android.Crashlytics;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {

    FlatButton button;

    ListView listView;

    List<Macro> macroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.activity_main);
        button = (FlatButton) findViewById(R.id.button_pull_data);
        listView = (ListView) findViewById(R.id.list);
        FlatUI.setDefaultTheme(FlatUI.DARK);
        FlatUI.setActionBarTheme(this, FlatUI.DARK, false, false);
        init();
    }

    private void init(){
        setListeners();
    }

    private void setListeners(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_pull_data();
            }
        });
    }

    void button_pull_data() {
        parseObjectPoolPULL();
    }

    private void parseObjectPoolPULL() {
        // Local file example
        try {
            Serializer serializer = new Persister();

            ObjectPool objectPool = serializer.read(ObjectPool.class, getAssets().open("display.xml"));
            //XMLPullParserHandler parserHandler = new XMLPullParserHandler();
            //macroList = parserHandler.parse(getAssets().open("display.xml"));

            ArrayAdapter<Macro> adapter = new ArrayAdapter<Macro>(this, R.layout.list_item, objectPool.macros);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
