package com.candroid.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.candroid.api.Client;
import com.candroid.app.BuildConfig;
import com.candroid.app.R;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.util.UIBuilder;
import com.candroid.app.views.DataMaskFragment;
import com.candroid.app.views.SoftKeyMaskFragment;
import com.cengalabs.flatui.FlatUI;
import com.crashlytics.android.Crashlytics;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements DataMaskFragment.OnFragmentInteractionListener, SoftKeyMaskFragment.OnFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    RelativeLayout layout;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.layout);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.GRASS);
        if (getActionBar() != null) {
            getActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRASS, false));
        }
    }

    public void showFragment(ArrayList<Fragment> fragments, int fragmentIndex, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == fragmentIndex) {
                transaction.show(fragments.get(i));
            } else {
                transaction.hide(fragments.get(i));
            }
        }
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void showSoftKeyMaskLeftFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_soft_key_mask_left, fragment)
                    .commit();
        }
    }

    public void showSoftKeyMaskRightFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_soft_key_mask_right, fragment)
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
                if (!BuildConfig.MOCK) {
                    Client client = new Client(this);
                    client.startSocketClient();
                } else {
                    mockInitialData();
                }
                return true;
            case R.id.action_graphics:
                startActivity(new Intent(this, ShapeDrawingExampleActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i(TAG, uri.toString());
    }

    @Override
    public void onSoftKeyClicked(int id) {
        Log.i(TAG, "ID: " + String.valueOf(id));
    }

    private void mockInitialData() {
        try {
            Serializer serializer = new Persister();
            ObjectPool objectPool = serializer.read(ObjectPool.class, getAssets().open("display.xml"));
            UIBuilder builder = new UIBuilder(this, this, layout)
                    .createLayout(objectPool)
                    .initialize();
            // Set Current Masks
            builder.setDataMask(objectPool.working_set.active_mask);
            // TODO: Bad idea to hardcode this value:
            builder.setSoftKeyMaskRight(objectPool.soft_key_mask.get(0).id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initDisplay(ObjectPool objectPool) {
        try {
            UIBuilder builder = new UIBuilder(this, this, layout)
                    .createLayout(objectPool)
                    .initialize();
            // Set Current Masks
            builder.setDataMask(objectPool.working_set.active_mask);
            // TODO: Bad idea to hardcode this value:
            builder.setSoftKeyMaskRight(objectPool.soft_key_mask.get(0).id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
