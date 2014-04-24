package com.candroid.app;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.candroid.app.util.XMLPullParserHandler;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.ParseException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById(R.id.button_pull_data)
    FlatButton button;

    @ViewById(R.id.list)
    ListView listView;

    List<Macro> macroList;

    @AfterViews
    void init() {
        FlatUI.setDefaultTheme(FlatUI.DARK);
        FlatUI.setActionBarTheme(this, FlatUI.DARK, false, false);
    }

    @Click
    void button_pull_data() {
        parseObjectPoolPULL();
    }

    private void parseObjectPoolVTD(String xmlString, byte[] data) {
        VTDGen vg = new VTDGen();
        vg.setDoc(data);
        try {
            vg.parse(true); //set namespace awareness to true
        } catch (ParseException e) {
            e.printStackTrace();
        }
        VTDNav vn = vg.getNav();
        AutoPilot ap = new AutoPilot(vn);
        // Select namespace here; * matches any local name
        //ap.selectElementNS("http://purl.org/dc/elements/1.1/", "*");
        try {
            ap.selectXPath("/objectpool/workingset");
            while (ap.evalXPath() != -1) {
                int val = vn.getAttrVal("name");
                if (val != -1) {
                    String name = vn.toNormalizedString(val);
                    System.out.println("Name:" + name);
                }
            }
        } catch (XPathParseException e) {
            e.printStackTrace();
        } catch (XPathEvalException e) {
            e.printStackTrace();
        } catch (NavException e) {
            e.printStackTrace();
        }
    }

    private void parseObjectPoolPULL() {
        // Local file example
        try {
            XMLPullParserHandler parserHandler = new XMLPullParserHandler();
            macroList = parserHandler.parse(getAssets().open("display.xml"));
            ArrayAdapter<Macro> adapter = new ArrayAdapter<Macro>(this, R.layout.list_item, macroList);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
