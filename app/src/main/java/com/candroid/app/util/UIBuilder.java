package com.candroid.app.util;

import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.candroid.app.dto.Container;
import com.candroid.app.dto.DataMask;
import com.candroid.app.dto.IncludeObject;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.dto.OutputNumber;
import com.candroid.app.dto.OutputString;

import java.util.ArrayList;

public class UIBuilder {

    /**
     * Compliant with the ISO format for Virtual Terminals
     *
     * @param context
     * @param view       The Root Layout's View to add items too.
     * @param objectPool The Root object pool sent across from the server and generated from the .iop file
     * @return True if the layout succeeded, False if it errors out in any way.
     */
    public static boolean setLayout(Context context, View view, ObjectPool objectPool) {
        // Must create all of the children and then the parents, adding the children to the parents
        // that they contain. Finally adding the parents to our root view.

        // Set the WorkingSet's id as our Key for getting/setting TAGS since we must use TAGS instead of ID's as our unique identifier.
        ArrayList<View> viewsList = new ArrayList<View>();
        ArrayList<RelativeLayout> containerList = new ArrayList<RelativeLayout>();
        // Cast our view to specific Layout:
        RelativeLayout layout = (RelativeLayout) view;
        // B.7: Buttons:
        for (com.candroid.app.dto.Button vtButton : objectPool.buttons) {
            Button uiButton = new Button(context);
            uiButton.setId(vtButton.id);
            uiButton.setTag(vtButton.name);
            uiButton.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtButton.width, context), Convert.convertDpToPixel(vtButton.height, context)));
            uiButton.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + vtButton.background_colour, "color", context.getPackageName())));
            uiButton.setText(vtButton.name);
            viewsList.add(uiButton);
        }
        // B.8.4 Input Number Object:
        for (com.candroid.app.dto.InputNumber vtInputNumber : objectPool.inputNumbers) {
            EditText uiEditText = new EditText(context);
            uiEditText.setId(vtInputNumber.id);
            uiEditText.setTag(vtInputNumber.name);
            // We are not worrying about the height, in order to show full text, use WRAP_CONTENT
            // If height is required, change the TextSize to the specified matching height.
            uiEditText.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtInputNumber.width, context), ViewGroup.LayoutParams.WRAP_CONTENT));
            if (vtInputNumber.number_of_decimals == 0) {
                uiEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else {
                uiEditText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }
            uiEditText.setFilters(new InputFilter[]{new InputFilterMinMax(vtInputNumber.min_value, vtInputNumber.max_value)});
            viewsList.add(uiEditText);
        }
        // B.9.2 Output String Object:
        for (OutputString vtOutputString : objectPool.outputStrings) {
            TextView uiTextView = new TextView(context);
            uiTextView.setId(vtOutputString.id);
            uiTextView.setTag(vtOutputString.name);
            uiTextView.setText(vtOutputString.value);
            uiTextView.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtOutputString.width, context), Convert.convertDpToPixel(vtOutputString.height, context)));
            viewsList.add(uiTextView);
        }
        // B.9.3 Output Number Object:
        for (OutputNumber vtOutputNumber : objectPool.outputNumbers) {
            float displayValue = (vtOutputNumber.value + vtOutputNumber.offset) * vtOutputNumber.scale;
            TextView uiTextView = new TextView(context);
            uiTextView.setId(vtOutputNumber.id);
            uiTextView.setTag(vtOutputNumber.name);
            uiTextView.setText(String.valueOf(displayValue));
            uiTextView.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtOutputNumber.width, context), Convert.convertDpToPixel(vtOutputNumber.height, context)));
            viewsList.add(uiTextView);
        }
        // B.4 Container Objects:
        // TODO: Think about using fragments for Containers
        for (Container vtContainer : objectPool.containers) {
            RelativeLayout uiLayout = new RelativeLayout(context);
            uiLayout.setId(vtContainer.id);
            uiLayout.setTag(vtContainer.name);
            uiLayout.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtContainer.width, context), Convert.convertDpToPixel(vtContainer.height, context)));
            if (vtContainer.hidden == 0) {
                uiLayout.setVisibility(View.VISIBLE);
            } else if (vtContainer.hidden == 1) {
                uiLayout.setVisibility(View.INVISIBLE);
            }
            for (IncludeObject includeObject : vtContainer.includeObjects) {
                for (View viewItem : viewsList) {
                    if (viewItem.getTag().equals(includeObject.name)) {
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewItem.getLayoutParams();
                        params.leftMargin = includeObject.pos_x;
                        params.topMargin = includeObject.pos_y;
                        viewItem.setLayoutParams(params);
                        // TODO: Not good programming!
                        if (((RelativeLayout) viewItem.getParent()) != null) {
                            ((RelativeLayout) viewItem.getParent()).removeView(viewItem);
                        }
                        uiLayout.addView(viewItem);
                    }
                }
            }
            containerList.add(uiLayout);
        }
        // B.2 DataMask Objects:
        for (DataMask vtDataMask : objectPool.dataMasks) {
            RelativeLayout uiLayout = new RelativeLayout(context);
            uiLayout.setId(vtDataMask.id);
            uiLayout.setTag(vtDataMask.name);
            uiLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            if (Convert.isNumeric(vtDataMask.background_colour)){
                uiLayout.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + vtDataMask.background_colour, "color", context.getPackageName())));
            } else {
                // Attempt to Parse Color:
                uiLayout.setBackgroundColor(Color.parseColor(vtDataMask.background_colour));
            }
            for (IncludeObject includeObject : vtDataMask.includeObjects) {
                for (RelativeLayout container : containerList) {
                    if (container.getTag().equals(includeObject.name)) {
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) container.getLayoutParams();
                        params.leftMargin = includeObject.pos_x;
                        params.topMargin = includeObject.pos_y;
                        container.setLayoutParams(params);
                        // TODO: Not good programming!
                        if (((RelativeLayout) container.getParent()) != null) {
                            ((RelativeLayout) container.getParent()).removeView(container);
                        }
                        uiLayout.addView(container);
                    }
                }
            }
            layout.addView(uiLayout);
        }
        return true;
    }

    /* Helper Methods */
    public static RelativeLayout buildRelativeLayout() {

        return null;
    }

    public static TextView buildTextView() {

        return null;
    }

    public static EditText buildEditText() {

        return null;
    }

}
