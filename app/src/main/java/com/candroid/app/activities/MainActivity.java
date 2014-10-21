package com.candroid.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.candroid.api.Client;
import com.candroid.app.R;
import com.candroid.app.dto.Macro;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.util.UIBuilder;
import com.cengalabs.flatui.FlatUI;
import com.crashlytics.android.Crashlytics;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class MainActivity extends Activity {

    RelativeLayout layout;
    ProgressBar progressBar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.layout);
        progressBar = (SmoothProgressBar) findViewById(R.id.progress_bar);
        FlatUI.setDefaultTheme(FlatUI.DARK);
        FlatUI.setActionBarTheme(this, FlatUI.DARK, false, false);
    }

    private void mockInitialData() {
        // Local file example
        try {
            Serializer serializer = new Persister();

            ObjectPool objectPool = serializer.read(ObjectPool.class, getAssets().open("display.xml"));
            //XMLPullParserHandler parserHandler = new XMLPullParserHandler();
            //macroList = parserHandler.parse(getAssets().open("display.xml"));
            UIBuilder builder = new UIBuilder();
            builder.setLayout(this, layout, objectPool);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_start:
                // TODO: Use build variants to test with Prod vs Dev
                //Client.startSocketClient();
                progressBar.setVisibility(View.VISIBLE);
                mockInitialData();
                progressBar.setVisibility(View.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
