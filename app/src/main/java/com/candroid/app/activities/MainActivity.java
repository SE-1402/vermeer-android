package com.candroid.app.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.candroid.app.R;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.util.UIBuilder;
import com.candroid.app.views.DataMaskFragment;
import com.cengalabs.flatui.FlatUI;
import com.crashlytics.android.Crashlytics;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;

public class MainActivity extends FragmentActivity implements DataMaskFragment.OnFragmentInteractionListener{

    public static final String TAG = MainActivity.class.getSimpleName();

    RelativeLayout layout;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.layout);
        FlatUI.setDefaultTheme(FlatUI.DARK);
        FlatUI.setActionBarTheme(this, FlatUI.DARK, false, false);
    }

    private void mockInitialData() {
        // Local file example
        try {
            Serializer serializer = new Persister();
            ObjectPool objectPool = serializer.read(ObjectPool.class, getAssets().open("display.xml"));
            UIBuilder builder = new UIBuilder(this, this, layout);
            builder.createLayout(objectPool);
            builder.setDataMask(objectPool.workingset.active_mask);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_data_mask, fragment)
                    .commit();
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
                mockInitialData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i(TAG, uri.toString());
    }
}
