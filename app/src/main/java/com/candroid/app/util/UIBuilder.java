package com.candroid.app.util;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.candroid.app.R;
import com.candroid.app.dto.Button;
import com.candroid.app.dto.ObjectPool;

public class UIBuilder {

    public static boolean setLayout(Context context, View view, ObjectPool objectPool){
        // Cast our view to specific Layout:
        RelativeLayout layout = (RelativeLayout) view;
        try{

            // B.7: Buttons:
            for (Button button : objectPool.buttons){
                android.widget.Button uiButton = new android.widget.Button(context);
                layout.addView(uiButton);
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static RelativeLayout buildRelativeLayout(){

        return null;
    }

    public static TextView buildTextView(){

        return null;
    }

    public static EditText buildEditText(){

        return null;
    }

}
