package com.candroid.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById(R.id.graph1)
    LinearLayout layout;

    @AfterViews
    void init() {
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(1, 2.0d),
                new GraphView.GraphViewData(2, 1.5d),
                new GraphView.GraphViewData(3, 2.5d),
                new GraphView.GraphViewData(4, 1.0d)
        });

        GraphView graphView = new LineGraphView(this, "GraphViewDemo");
        graphView.addSeries(exampleSeries);
        layout.addView(graphView);
    }
}
