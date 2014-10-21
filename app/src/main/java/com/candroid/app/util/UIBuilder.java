package com.candroid.app.util;

import android.app.ActionBar;
import android.app.Activity;
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

import com.candroid.app.R;
import com.candroid.app.dto.Container;
import com.candroid.app.dto.DataMask;
import com.candroid.app.dto.IncludeMacro;
import com.candroid.app.dto.IncludeObject;
import com.candroid.app.dto.InputNumber;
import com.candroid.app.dto.Key;
import com.candroid.app.dto.Macro;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.dto.OutputNumber;
import com.candroid.app.dto.OutputString;
import com.candroid.app.dto.SoftKeyMask;

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
        ArrayList<Macro> macroList = new ArrayList<Macro>();
        // Cast our view to specific Layout:
        RelativeLayout rootView = (RelativeLayout) view;
        RelativeLayout dataMaskLayout = (RelativeLayout) view.findViewById(R.id.layout_data_mask);
        RelativeLayout softKeyMaskLeftLayout = (RelativeLayout) view.findViewById(R.id.layout_soft_key_mask_left);
        RelativeLayout softKeyMaskRightLayout = (RelativeLayout) view.findViewById(R.id.layout_soft_key_mask_right);

        // B.1 Working Set:
        ActionBar actionBar = ((Activity) context).getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(objectPool.workingset.outputstring.value);
        }
        if (Convert.isNumeric(objectPool.workingset.background_colour)) {
            rootView.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + objectPool.workingset.background_colour, "color", context.getPackageName())));
        } else {
            // Attempt to Parse Color:
            rootView.setBackgroundColor(Color.parseColor(objectPool.workingset.background_colour));
        }
        // B.6 Key Object:
        for (Key vtKey : objectPool.keys) {
            createVTKey(context, viewsList, macroList, vtKey);
        }
        // B.7 Button Object:
        for (com.candroid.app.dto.Button vtButton : objectPool.buttons) {
            createVTButton(context, viewsList, vtButton);
        }
        // B.8.4 Input Number Object:
        for (com.candroid.app.dto.InputNumber vtInputNumber : objectPool.inputNumbers) {
            createVTInputNumber(context, viewsList, vtInputNumber);
        }
        // B.9.2 Output String Object:
        for (OutputString vtOutputString : objectPool.outputStrings) {
            createVTOutputString(context, viewsList, vtOutputString);
        }
        // B.9.3 Output Number Object:
        for (OutputNumber vtOutputNumber : objectPool.outputNumbers) {
            createVTOutputNumber(context, viewsList, vtOutputNumber);
        }

        // B.4 Container Objects:
        // TODO: Think about using fragments for Containers
        // Then we can swap out the R.id.layout_data_mask object for the current one.
        for (Container vtContainer : objectPool.containers) {
            createVTContainer(context, viewsList, vtContainer);
        }

        /* Add DataMask and SoftKeyMask Objects last */
        // B.2 DataMask Objects:
        for (DataMask vtDataMask : objectPool.dataMasks) {
            createVTDataMask(context, objectPool, viewsList, dataMaskLayout, vtDataMask);
        }
        // B.3 SoftKeyMask Objects:
        for (SoftKeyMask vtSoftKeyMask : objectPool.softKeyMasks) {
            createVTSoftKeyMask(context, objectPool, viewsList, softKeyMaskLeftLayout, vtSoftKeyMask);
        }
        return true;
    }

    private static void createVTKey(Context context, ArrayList<View> viewsList, ArrayList<Macro> macroList, Key vtKey){
        RelativeLayout uiLayout = new RelativeLayout(context);
        uiLayout.setId(vtKey.id);
        uiLayout.setTag(vtKey.name);
        uiLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for (IncludeObject includeObject : vtKey.include_object) {
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
        for (IncludeMacro includeMacro : vtKey.include_macro) {
            if (includeMacro.event.equals("on_key_press")){
                for (Macro macro : macroList) {
                    if (macro.name.equals(includeMacro.name)){
                        // Update Event List (Hook up View/Controller for UI Changes)
                    }
                }
            }
        }
        viewsList.add(uiLayout);
    }

    private static void createVTButton(Context context, ArrayList<View> viewsList, com.candroid.app.dto.Button vtButton) {
        Button uiButton = new Button(context);
        uiButton.setId(vtButton.id);
        uiButton.setTag(vtButton.name);
        uiButton.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtButton.width, context), Convert.convertDpToPixel(vtButton.height, context)));
        uiButton.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + vtButton.background_colour, "color", context.getPackageName())));
        uiButton.setText(vtButton.name);
        viewsList.add(uiButton);
    }

    private static void createVTInputNumber(Context context, ArrayList<View> viewsList, InputNumber vtInputNumber) {
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

    private static void createVTOutputString(Context context, ArrayList<View> viewsList, OutputString vtOutputString) {
        TextView uiTextView = new TextView(context);
        uiTextView.setId(vtOutputString.id);
        uiTextView.setTag(vtOutputString.name);
        uiTextView.setText(vtOutputString.value);
        uiTextView.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtOutputString.width, context), Convert.convertDpToPixel(vtOutputString.height, context)));
        viewsList.add(uiTextView);
    }

    private static void createVTOutputNumber(Context context, ArrayList<View> viewsList, OutputNumber vtOutputNumber) {
        float displayValue = (vtOutputNumber.value + vtOutputNumber.offset) * vtOutputNumber.scale;
        TextView uiTextView = new TextView(context);
        uiTextView.setId(vtOutputNumber.id);
        uiTextView.setTag(vtOutputNumber.name);
        uiTextView.setText(String.valueOf(displayValue));
        uiTextView.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtOutputNumber.width, context), Convert.convertDpToPixel(vtOutputNumber.height, context)));
        viewsList.add(uiTextView);
    }

    private static void createVTContainer(Context context, ArrayList<View> viewsList, Container vtContainer) {
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
        viewsList.add(uiLayout);
    }

    private static void createVTDataMask(Context context, ObjectPool objectPool, ArrayList<View> viewsList, RelativeLayout dataMaskLayout, DataMask vtDataMask) {
        if (vtDataMask.name.equals(objectPool.workingset.active_mask)) {
            RelativeLayout uiLayout = new RelativeLayout(context);
            uiLayout.setId(vtDataMask.id);
            uiLayout.setTag(vtDataMask.name);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            uiLayout.setLayoutParams(params);
            if (Convert.isNumeric(vtDataMask.background_colour)) {
                uiLayout.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + vtDataMask.background_colour, "color", context.getPackageName())));
            } else {
                // Attempt to Parse Color:
                uiLayout.setBackgroundColor(Color.parseColor(vtDataMask.background_colour));
            }
            for (IncludeObject includeObject : vtDataMask.includeObjects) {
                for (View container : viewsList) {
                    if (container.getTag().equals(includeObject.name)) {
                        RelativeLayout.LayoutParams paramsContainer = (RelativeLayout.LayoutParams) container.getLayoutParams();
                        paramsContainer.leftMargin = includeObject.pos_x;
                        paramsContainer.topMargin = includeObject.pos_y;
                        container.setLayoutParams(paramsContainer);
                        // TODO: Not good programming!
                        if (((RelativeLayout) container.getParent()) != null) {
                            ((RelativeLayout) container.getParent()).removeView(container);
                        }
                        uiLayout.addView(container);
                    }
                }
            }
            dataMaskLayout.addView(uiLayout);
        }
    }

    private static void createVTSoftKeyMask(Context context, ObjectPool objectPool, ArrayList<View> viewsList, RelativeLayout softKeyMaskLayout, SoftKeyMask vtSoftKeyMask) {
        RelativeLayout uiLayout = new RelativeLayout(context);
        uiLayout.setId(vtSoftKeyMask.id);
        uiLayout.setTag(vtSoftKeyMask.name);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        uiLayout.setLayoutParams(params);
        uiLayout.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + vtSoftKeyMask.background_colour, "color", context.getPackageName())));
        for (IncludeObject includeObject : vtSoftKeyMask.includeObjects) {
            for (View key : viewsList) {
                if (key.getTag().equals(includeObject.name)) {
                    // TODO: Not good programming!
                    if (((RelativeLayout) key.getParent()) != null) {
                        ((RelativeLayout) key.getParent()).removeView(key);
                    }
                    uiLayout.addView(key);
                }
            }
        }
        softKeyMaskLayout.addView(uiLayout);
    }
}
