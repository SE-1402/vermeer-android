package com.candroid.app.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.candroid.app.R;
import com.candroid.app.activities.MainActivity;
import com.candroid.app.dto.Container;
import com.candroid.app.dto.DataMask;
import com.candroid.app.dto.IncludeObject;
import com.candroid.app.dto.InputNumber;
import com.candroid.app.dto.Key;
import com.candroid.app.dto.IncludeMacro;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.dto.OutputNumber;
import com.candroid.app.dto.OutputString;
import com.candroid.app.dto.SoftKeyMask;
import com.candroid.app.views.DataMaskFragment;
import com.candroid.app.views.SoftKeyMaskFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class UIBuilder {

    Activity activity;
    Context context;
    View view;

    RelativeLayout rootView;
    FrameLayout dataMaskLayout;
    FrameLayout softKeyMaskLeftLayout;
    FrameLayout softKeyMaskRightLayout;

    ArrayList<View> viewsList;
    ArrayList<Fragment> dataMaskFragmentList;
    ArrayList<Fragment> softKeyMaskFragmentList;
    HashMap<String, View.OnClickListener> macrosList;

    public UIBuilder(Activity activity, Context context, View view) {
        this.activity = activity;
        this.context = context;
        this.view = view;
        viewsList = new ArrayList<View>();
        dataMaskFragmentList = new ArrayList<Fragment>();
        softKeyMaskFragmentList = new ArrayList<Fragment>();
        macrosList = new HashMap<String, View.OnClickListener>();
    }

    /**
     * Compliant with the ISO format for Virtual Terminals
     *
     * @param objectPool The Root object pool sent across from the server and generated from the .iop file
     * @return True if the layout succeeded, False if it errors out in any way.
     */
    public UIBuilder createLayout(ObjectPool objectPool) {
        // Cast our view to specific Layout:
        rootView = (RelativeLayout) view;
        dataMaskLayout = (FrameLayout) view.findViewById(R.id.layout_data_mask);
        softKeyMaskLeftLayout = (FrameLayout) view.findViewById(R.id.layout_soft_key_mask_left);
        softKeyMaskRightLayout = (FrameLayout) view.findViewById(R.id.layout_soft_key_mask_right);

        // B.1 Working Set:
        ActionBar actionBar = ((Activity) context).getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(objectPool.workingset.outputstring.get(0).value);
        }
        if (Convert.isNumeric(objectPool.workingset.background_colour)) {
            rootView.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + objectPool.workingset.background_colour, "color", context.getPackageName())));
        } else {
            // Attempt to Parse Color:
            rootView.setBackgroundColor(Color.parseColor(objectPool.workingset.background_colour));
        }
        // B.7 Button Object:
        for (com.candroid.app.dto.Button vtButton : objectPool.button) {
            createVTButton(vtButton);
        }
        // B.8.4 Input Number Object:
        for (com.candroid.app.dto.InputNumber vtInputNumber : objectPool.inputNumber) {
            createVTInputNumber(vtInputNumber);
        }
        // B.9.2 Output String Object:
        for (OutputString vtOutputString : objectPool.outputString) {
            createVTOutputString(vtOutputString);
        }
        // B.9.3 Output Number Object:
        for (OutputNumber vtOutputNumber : objectPool.outputNumber) {
            createVTOutputNumber(vtOutputNumber);
        }

        // B.4 Container Objects:
        for (Container vtContainer : objectPool.container) {
            createVTContainer(vtContainer);
        }

        /* Add DataMask and SoftKeyMask Objects last */
        // TODO: Use fragments for Masks (Data & SoftKeys)
        // Then we can swap out the R.id.layout_data_mask object for the current one.
        // B.2 DataMask Objects:
        for (DataMask vtDataMask : objectPool.dataMask) {
            createVTDataMask(objectPool, vtDataMask);
        }
        // B.16 Macro Objects:
        for (IncludeMacro vtIncludeMacro : objectPool.includeMacro) {
            createVTMacro(objectPool, vtIncludeMacro);
        }
        // B.6 Key Object:
        for (Key vtKey : objectPool.key) {
            createVTKey(vtKey);
        }
        // B.3 SoftKeyMask Objects:
        for (SoftKeyMask vtSoftKeyMask : objectPool.softKeyMask) {
            createVTSoftKeyMask(objectPool, vtSoftKeyMask);
        }
        return this;
    }

    public UIBuilder initialize() {
        for (Fragment fragment : dataMaskFragmentList) {
            ((MainActivity) activity).getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_data_mask, fragment)
                    .commit();
        }
        return this;
    }

    public void setDataMask(String activeMaskName) {
        int index = 0;
        for (Fragment fragment : dataMaskFragmentList) {
            if (((DataMaskFragment) fragment).getName().equals(activeMaskName)) {
                ((MainActivity) activity).showFragment(dataMaskFragmentList, index, false);
            } else {
                ++index;
            }
        }
    }

    public void setSoftKeyMaskLeft(String activeMaskName) {
        for (Fragment fragment : softKeyMaskFragmentList) {
            if (((SoftKeyMaskFragment) fragment).getName().equals(activeMaskName)) {
                ((MainActivity) activity).showSoftKeyMaskLeftFragment(fragment);
            }
        }
    }

    public void setSoftKeyMaskRight(String activeMaskName) {
        for (Fragment fragment : softKeyMaskFragmentList) {
            if (((SoftKeyMaskFragment) fragment).getName().equals(activeMaskName)) {
                ((MainActivity) activity).showSoftKeyMaskRightFragment(fragment);
            }
        }
    }

    private void createVTMacro(final ObjectPool objectPool, final IncludeMacro vtIncludeMacro) {
        View.OnClickListener command = null;
        if (vtIncludeMacro.command_change_numeric_value != null) {
            command = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (View view : viewsList) {
                        // Change value
                    }
                }
            };
        } else if (vtIncludeMacro.command_change_active_mask != null) {
            command = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (objectPool.workingset.name.equals(vtIncludeMacro.command_change_active_mask.working_set_object_id)) {
                        setDataMask(vtIncludeMacro.command_change_active_mask.new_active_mask_object_id);
                    }
                }
            };
        }
        macrosList.put(vtIncludeMacro.name, command);
    }

    private void createVTKey(Key vtKey) {
        Button button = new Button(context);
        button.setId(vtKey.id);
        button.setTag(vtKey.name);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for (View viewItem : viewsList) {
            if (viewItem.getTag().equals(vtKey.include_object.name)) {
                if (viewItem instanceof ImageView) {
                    ImageView image = (ImageView) viewItem;
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        button.setBackgroundDrawable(image.getDrawable());
                    } else {
                        button.setBackground(image.getDrawable());
                    }
                } else if (viewItem instanceof TextView) {
                    TextView text = (TextView) viewItem;
                    button.setText(text.getText());
                }
            }

        }
        if (vtKey.include_macro.event.equals("on_key_press")) {
            View.OnClickListener macro = macrosList.get(vtKey.include_macro.name);
            button.setOnClickListener(macro);
        }
        viewsList.add(button);
    }

    private void createVTButton(com.candroid.app.dto.Button vtButton) {
        Button uiButton = new Button(context);
        uiButton.setId(vtButton.id);
        uiButton.setTag(vtButton.name);
        uiButton.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtButton.width, context), Convert.convertDpToPixel(vtButton.height, context)));
        uiButton.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + vtButton.background_colour, "color", context.getPackageName())));
        uiButton.setText(vtButton.name);
        viewsList.add(uiButton);
    }

    private void createVTInputNumber(InputNumber vtInputNumber) {
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

    private void createVTOutputString(OutputString vtOutputString) {
        TextView uiTextView = new TextView(context);
        uiTextView.setId(vtOutputString.id);
        uiTextView.setTag(vtOutputString.name);
        uiTextView.setText(vtOutputString.value);
        uiTextView.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtOutputString.width, context), Convert.convertDpToPixel(vtOutputString.height, context)));
        viewsList.add(uiTextView);
    }

    private void createVTOutputNumber(OutputNumber vtOutputNumber) {
        float displayValue = (vtOutputNumber.value + vtOutputNumber.offset) * vtOutputNumber.scale;
        TextView uiTextView = new TextView(context);
        uiTextView.setId(vtOutputNumber.id);
        uiTextView.setTag(vtOutputNumber.name);
        uiTextView.setText(String.valueOf(displayValue));
        uiTextView.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtOutputNumber.width, context), Convert.convertDpToPixel(vtOutputNumber.height, context)));
        viewsList.add(uiTextView);
    }

    private void createVTContainer(Container vtContainer) {
        RelativeLayout uiLayout = new RelativeLayout(context);
        uiLayout.setId(vtContainer.id);
        uiLayout.setTag(vtContainer.name);
        uiLayout.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(vtContainer.width, context), Convert.convertDpToPixel(vtContainer.height, context)));
        if (vtContainer.hidden == 0) {
            uiLayout.setVisibility(View.VISIBLE);
        } else if (vtContainer.hidden == 1) {
            uiLayout.setVisibility(View.INVISIBLE);
        }
        for (IncludeObject includeObject : vtContainer.object) {
            for (View viewItem : viewsList) {
                if (viewItem.getTag().equals(includeObject.name)) {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewItem.getLayoutParams();
                    params.leftMargin = includeObject.pos_x;
                    params.topMargin = includeObject.pos_y;
                    viewItem.setLayoutParams(params);
                    // TODO: Not good programming!
                    // TODO: FIX THIS
                    if (((RelativeLayout) viewItem.getParent()) != null) {
                        ((RelativeLayout) viewItem.getParent()).removeView(viewItem);
                    }
                    uiLayout.addView(viewItem);
                }
            }
        }
        viewsList.add(uiLayout);
    }

    private void createVTDataMask(ObjectPool objectPool, DataMask vtDataMask) {
        DataMaskFragment dataMaskFragment = DataMaskFragment.newInstance()
                .setName(vtDataMask.name)
                .setId(vtDataMask.id)
                .setBackgroundColor(vtDataMask.background_colour);

        for (IncludeObject includeObject : vtDataMask.includeObject) {
            for (View container : viewsList) {
                if (container.getTag().equals(includeObject.name)) {
                    RelativeLayout.LayoutParams paramsContainer = (RelativeLayout.LayoutParams) container.getLayoutParams();
                    paramsContainer.leftMargin = includeObject.pos_x;
                    paramsContainer.topMargin = includeObject.pos_y;
                    container.setLayoutParams(paramsContainer);
                    dataMaskFragment.addIncludeObject(container);
                }
            }
        }
        dataMaskFragmentList.add(dataMaskFragment);
    }

    private void createVTSoftKeyMask(ObjectPool objectPool, SoftKeyMask vtSoftKeyMask) {
        SoftKeyMaskFragment softKeyMaskFragment = SoftKeyMaskFragment.newInstance()
                .setId(vtSoftKeyMask.id)
                .setName(vtSoftKeyMask.name)
                .setBackgroundColor(vtSoftKeyMask.background_colour);

        for (IncludeObject includeObject : vtSoftKeyMask.object) {
            for (View key : viewsList) {
                if (key.getTag().equals(includeObject.name)) {
                    softKeyMaskFragment.addIncludeObject(key);
                }
            }
        }
        softKeyMaskFragmentList.add(softKeyMaskFragment);
    }
}
