package com.candroid.app.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.candroid.app.R;
import com.candroid.app.activities.MainActivity;
import com.candroid.app.dto.AlarmMask;
import com.candroid.app.dto.ArchedBarGraph;
import com.candroid.app.dto.Container;
import com.candroid.app.dto.DataMask;
import com.candroid.app.dto.Ellipse;
import com.candroid.app.dto.FillAttributes;
import com.candroid.app.dto.FontAttributes;
import com.candroid.app.dto.IncludeMacro;
import com.candroid.app.dto.IncludeObject;
import com.candroid.app.dto.InputBoolean;
import com.candroid.app.dto.InputList;
import com.candroid.app.dto.InputNumber;
import com.candroid.app.dto.InputString;
import com.candroid.app.dto.Key;
import com.candroid.app.dto.Line;
import com.candroid.app.dto.LineAttributes;
import com.candroid.app.dto.LinearBarGraph;
import com.candroid.app.dto.Macro;
import com.candroid.app.dto.Meter;
import com.candroid.app.dto.NumberVariable;
import com.candroid.app.dto.ObjectPointer;
import com.candroid.app.dto.ObjectPool;
import com.candroid.app.dto.OutputList;
import com.candroid.app.dto.OutputNumber;
import com.candroid.app.dto.OutputString;
import com.candroid.app.dto.PictureGraphic;
import com.candroid.app.dto.Polygon;
import com.candroid.app.dto.Rectangle;
import com.candroid.app.dto.SoftKeyMask;
import com.candroid.app.dto.StringVariable;
import com.candroid.app.dto.commands.ChangeActiveMask;
import com.candroid.app.views.DataMaskFragment;
import com.candroid.app.views.LineDrawable;
import com.candroid.app.views.RectangleDrawable;
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
    HashMap<Integer, View.OnClickListener> macrosList;

    public UIBuilder(Activity activity, Context context, View view) {
        this.activity = activity;
        this.context = context;
        this.view = view;
        viewsList = new ArrayList<View>();
        dataMaskFragmentList = new ArrayList<Fragment>();
        softKeyMaskFragmentList = new ArrayList<Fragment>();
        macrosList = new HashMap<Integer, View.OnClickListener>();
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

        // B.6 Key Object:
        if (objectPool.key != null) {
            for (Key key : objectPool.key) {
                createKey(objectPool, key);
            }
        }
        // B.7 Button Object:
        if (objectPool.button != null) {
            for (com.candroid.app.dto.Button button : objectPool.button) {
                createButton(objectPool, button);
            }
        }
        // B.8.2 Input Boolean Object:
        if (objectPool.input_boolean != null) {
            for (InputBoolean inputBoolean : objectPool.input_boolean) {
                createInputBoolean(inputBoolean);
            }
        }
        // B.8.3 Input String Object:
        if (objectPool.input_string != null) {
            for (InputString inputString : objectPool.input_string) {
                createInputString(objectPool, inputString);
            }
        }
        // B.8.4 Input Number Object:
        if (objectPool.input_string != null) {
            for (InputNumber inputNumber : objectPool.input_number) {
                createInputNumber(objectPool, inputNumber);
            }
        }
        // B.8.5
        if (objectPool.input_list != null) {
            for (InputList inputList : objectPool.input_list) {
                createInputList(inputList);
            }
        }
        // B.9.2 Output String Object:
        if (objectPool.output_string != null) {
            for (OutputString outputString : objectPool.output_string) {
                createOutputString(objectPool, outputString);
            }
        }
        // B.9.3 Output Number Object:
        if (objectPool.output_number != null) {
            for (OutputNumber outputNumber : objectPool.output_number) {
                createOutputNumber(objectPool, outputNumber);
            }
        }
        // B.9.4 Output List Object:
        if (objectPool.output_list != null) {
            for (OutputList outputList : objectPool.output_list) {
                createOutputList(outputList);
            }
        }

        /* Graphics Related */

        // B.10.2 Line Object:
        if (objectPool.line != null) {
            for (Line line : objectPool.line) {
                createLine(line);
            }
        }
        // B.10.3 Rectangle Object:
        if (objectPool.rectangle != null) {
            for (Rectangle rectangle : objectPool.rectangle) {
                createRectangle(objectPool, rectangle);
            }
        }
        // B.10.4 Ellipse Object:
        if (objectPool.ellipse != null) {
            for (Ellipse ellipse : objectPool.ellipse) {
                createEllipse(ellipse);
            }
        }
        // B.10.5 Polygon Object:
        if (objectPool.polygon != null) {
            for (Polygon polygon : objectPool.polygon) {
                createPolygon(polygon);
            }
        }
        // B.11.2 Meter Object:
        if (objectPool.meter != null) {
            for (Meter meter : objectPool.meter) {
                createMeter(meter);
            }
        }
        // B.11.3 Linear Bar Graph Object:
        if (objectPool.linear_bar_graph != null) {
            for (LinearBarGraph linearBarGraph : objectPool.linear_bar_graph) {
                createLinearBarGraph(linearBarGraph);
            }
        }
        // B.11.4 Arched Bar Graph Object:
        if (objectPool.arched_bar_graph != null) {
            for (ArchedBarGraph archedBarGraph : objectPool.arched_bar_graph) {
                createArchedBarGraph(archedBarGraph);
            }
        }
        // B.12 Picture Graphic Object:
        if (objectPool.picture_graphic != null) {
            for (PictureGraphic pictureGraphic : objectPool.picture_graphic) {
                createPictureGraphic(pictureGraphic);
            }
        }
        // B.13.2 Number Variable Object: Used in individual object creation methods.
        // B.13.3 String Variable Object: Used in individual object creation methods.
        // B.14.2 Font Attr Object: Used in individual object creation methods.
        // B.14.3 Line Attr Object: Used in individual object creation methods.
        // B.14.4 Fill Attr Object: Used in individual object creation methods.
        // B.14.5 Input Attr Object: Used in individual object creation methods.
        // B.15 Object Pointer Object:
        if (objectPool.object_pointer != null) {
            for (ObjectPointer objectPointer : objectPool.object_pointer) {
                createObjectPointer(objectPointer);
            }
        }
        // B.16 Macro Objects: Used in individual object creation methods.

        /* Add DataMask and SoftKeyMask Objects last */

        // B.2 DataMask Objects:
        if (objectPool.data_mask != null) {
            for (DataMask dataMask : objectPool.data_mask) {
                createDataMask(objectPool, dataMask);
            }
        }
        // B.3 AlarmMask Objects:
        if (objectPool.alarm_mask != null) {
            for (AlarmMask alarmMask : objectPool.alarm_mask) {
                createAlarmMask(alarmMask);
            }
        }
        // B.4 Container Objects:
        if (objectPool.container != null) {
            for (Container container : objectPool.container) {
                createContainer(objectPool, container);
            }
        }
        // B.5 SoftKeyMask Objects:
        if (objectPool.soft_key_mask != null) {
            for (SoftKeyMask softKeyMask : objectPool.soft_key_mask) {
                createSoftKeyMask(softKeyMask);
            }
        }

        /* Finalize the overall view of the VT */

        // B.1 Working Set:
        ActionBar actionBar = ((Activity) context).getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("Candroid Application");
        }
        if (objectPool.working_set != null) {
            rootView.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + objectPool.working_set.background_colour, "color", context.getPackageName())));
        }
        return this;
    }

    public UIBuilder initialize() {
        for (Fragment fragment : dataMaskFragmentList) {
            FragmentTransaction transaction = ((MainActivity) activity).getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.layout_data_mask, fragment);
            transaction.commit();
        }
        return this;
    }

    public void setDataMask(int activeMaskId) {
        int index = 0;
        for (Fragment fragment : dataMaskFragmentList) {
            if (((DataMaskFragment) fragment).getMaskId() == (activeMaskId)) {
                ((MainActivity) activity).showFragment(dataMaskFragmentList, index, false);
            } else {
                ++index;
            }
        }
    }

    public void setSoftKeyMaskLeft(int activeMaskId) {
        for (Fragment fragment : softKeyMaskFragmentList) {
            if (((SoftKeyMaskFragment) fragment).getMaskId() == (activeMaskId)) {
                ((MainActivity) activity).showSoftKeyMaskLeftFragment(fragment);
            }
        }
    }

    public void setSoftKeyMaskRight(int activeMaskId) {
        for (Fragment fragment : softKeyMaskFragmentList) {
            if (((SoftKeyMaskFragment) fragment).getMaskId() == (activeMaskId)) {
                ((MainActivity) activity).showSoftKeyMaskRightFragment(fragment);
            }
        }
    }

    /**
     * Keys are equivalent to Buttons but only ussed within Soft Key Masks otherwise are clipped.
     *
     * @param key Key object to define and create
     */
    private void createKey(ObjectPool objectPool, Key key) {
        Button button = new Button(context);
        button.setId(key.id);
        button.setTag(key.id);
        button.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + key.background_colour, "color", context.getPackageName())));
        // TODO: Key Code???
        // TODO: Objects
        button.setText(String.valueOf(key.id));
        for (IncludeMacro includeMacro : key.include_macro) {
            for (Macro macro : objectPool.macro) {
                if (includeMacro.macro_id == macro.id) {
                    button.setOnClickListener(setMacro(macro));
                }
            }
        }
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        viewsList.add(button);
    }

    /**
     * Equivalent to a Key except can be used inside of the Data Mask
     *
     * @param button The Button to define and create.
     */
    private void createButton(ObjectPool objectPool, com.candroid.app.dto.Button button) {
        Button uiButton = new Button(context);
        uiButton.setId(button.id);
        uiButton.setTag(button.id);
        uiButton.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(button.width, context), Convert.convertDpToPixel(button.height, context)));
        uiButton.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + button.background_colour, "color", context.getPackageName())));
        // TODO: Border Color with Dynamic Drawable??
        // TODO: Key Code??
        String options = Integer.toBinaryString(button.options);
        // TODO: Options
        // TODO: Objects
        for (IncludeMacro includeMacro : button.include_macro) {
            for (Macro macro : objectPool.macro) {
                if (includeMacro.macro_id == macro.id) {
                    uiButton.setOnClickListener(setMacro(macro));
                }
            }
        }
        viewsList.add(uiButton);
    }

    private void createInputBoolean(InputBoolean inputBoolean) {
        CheckBox checkBox = new CheckBox(context);
        checkBox.setId(inputBoolean.id);
        checkBox.setTag(inputBoolean.id);
        checkBox.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + inputBoolean.background_colour, "color", context.getPackageName())));
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // Unless using AppCompatv7 or Android 5.0 Check mark cannot change color
        if (inputBoolean.variable_reference > 0 && inputBoolean.variable_reference < 65535) {
            // TODO: Use Variable Reference
        } else {
            checkBox.setChecked(inputBoolean.value > 0);
        }
        if (inputBoolean.enabled == 1) {
            checkBox.setEnabled(true);
        } else if (inputBoolean.enabled == 0) {
            checkBox.setEnabled(false);
        }
        viewsList.add(checkBox);
    }

    private void createInputString(ObjectPool objectPool, InputString inputString) {
        EditText editText = new EditText(context);
        editText.setId(inputString.id);
        editText.setTag(inputString.id);
        editText.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(inputString.width, context), Convert.convertDpToPixel(inputString.height, context)));
        editText.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + inputString.background_colour, "color", context.getPackageName())));
        // Font Attribute:
        if (inputString.font_attribute > 0 && inputString.font_attribute < 65535) {
            for (FontAttributes fontAttributes : objectPool.font_attribute) {
                if (fontAttributes.id == inputString.font_attribute) {
                    setFontFromAttribute(editText, fontAttributes);
                }
            }
        }
        // TODO: Input Attr
        String options = String.format("%3s", Integer.toBinaryString(inputString.options)).replace(' ', '0');
        if (options.substring(2, 3).equals("1")) {
            // Transparent
            editText.setBackgroundColor(Color.TRANSPARENT);
        }
        if (options.substring(1, 2).equals("1")) {
            // TODO: AutoWrap
        }
        if (inputString.variable_reference > 0 && inputString.variable_reference < 66535) {
            for (StringVariable stringVariable : objectPool.string_variable) {
                if (stringVariable.id == inputString.variable_reference) {
                    editText.setText(stringVariable.value);
                }
            }
        } else {
            editText.setText(inputString.value);
        }
        // TODO: justification
        String justification = Integer.toBinaryString(inputString.justification);
        // TODO: Is length needed???
        if (inputString.enabled == 1) {
            editText.setEnabled(true);
        } else if (inputString.enabled == 0) {
            editText.setEnabled(false);
        }
        for (IncludeMacro includeMacro : inputString.include_macro) {
            for (Macro macro : objectPool.macro) {
                if (includeMacro.macro_id == macro.id) {
                    editText.setOnClickListener(setMacro(macro));
                }
            }
        }
        viewsList.add(editText);
    }

    private void createInputNumber(ObjectPool objectPool, InputNumber inputNumber) {
        EditText editText = new EditText(context);
        editText.setId(inputNumber.id);
        editText.setTag(inputNumber.id);
        // We are not worrying about the height, in order to show full text, use WRAP_CONTENT
        // If height is required, change the TextSize to the specified matching height.
        editText.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(inputNumber.width, context), ViewGroup.LayoutParams.WRAP_CONTENT));
        editText.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + inputNumber.background_colour, "color", context.getPackageName())));
        // Font Attribute:
        if (inputNumber.font_attribute > 0 && inputNumber.font_attribute < 65535) {
            for (FontAttributes fontAttributes : objectPool.font_attribute) {
                if (fontAttributes.id == inputNumber.font_attribute) {
                    setFontFromAttribute(editText, fontAttributes);
                }
            }
        }
        String options = String.format("%4s", Integer.toBinaryString(inputNumber.options)).replace(' ', '0');
        if (options.substring(3, 4).equals("1")) {
            // Transparent
            editText.setBackgroundColor(Color.TRANSPARENT);
        }
        if (options.substring(2, 3).equals("1")) {
            // TODO: Leading Zeros
        }
        if (inputNumber.variable_reference > 0 && inputNumber.variable_reference < 66535) {
            for (NumberVariable numberVariable : objectPool.number_variable) {
                if (numberVariable.id == inputNumber.id) {
                    editText.setText(String.valueOf(numberVariable.value));
                }
            }
        } else {
            editText.setText(String.valueOf(inputNumber.value));
        }
        // TODO: Everything after Value
        if (inputNumber.number_of_decimals == 0) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        editText.setFilters(new InputFilter[]{new InputFilterMinMax(inputNumber.min_value, inputNumber.max_value)});
        viewsList.add(editText);
    }

    private void createInputList(InputList inputList) {
        // TODO: Input List
    }

    private void createOutputString(ObjectPool objectPool, OutputString outputString) {
        TextView textView = new TextView(context);
        textView.setId(outputString.id);
        textView.setTag(outputString.id);
        textView.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(outputString.width, context), Convert.convertDpToPixel(outputString.height, context)));
        textView.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + outputString.background_colour, "color", context.getPackageName())));
        // Font Attribute:
        if (outputString.font_attribute > 0 && outputString.font_attribute < 65535) {
            for (FontAttributes fontAttributes : objectPool.font_attribute) {
                if (fontAttributes.id == outputString.font_attribute) {
                    setFontFromAttribute(textView, fontAttributes);
                }
            }
        }
        String options = String.format("%2s", Integer.toBinaryString(outputString.options)).replace(' ', '0');
        if (options.substring(1, 2).equals("1")) {
            // Transparent
            textView.setBackgroundColor(Color.TRANSPARENT);
        }
        if (options.substring(0, 1).equals("1")) {
            // TODO: AutoWrap
        }
        if (outputString.variable_reference > 0 && outputString.variable_reference < 65535) {
            // TODO: Variable Ref
        } else {
            textView.setText(outputString.value);
        }
        // TODO: Justification
        // TODO: Does Length Matter???
        for (IncludeMacro includeMacro : outputString.include_macro) {
            for (Macro macro : objectPool.macro) {
                if (includeMacro.macro_id == macro.id) {
                    textView.setOnClickListener(setMacro(macro));
                }
            }
        }
        viewsList.add(textView);
    }

    private void createOutputNumber(ObjectPool objectPool, OutputNumber outputNumber) {
        TextView textView = new TextView(context);
        textView.setId(outputNumber.id);
        textView.setTag(outputNumber.id);
        textView.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(outputNumber.width, context), Convert.convertDpToPixel(outputNumber.height, context)));
        textView.setBackgroundColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + outputNumber.background_colour, "color", context.getPackageName())));
        // Font Attribute:
        if (outputNumber.font_attribute > 0 && outputNumber.font_attribute < 65535) {
            for (FontAttributes fontAttributes : objectPool.font_attribute) {
                if (fontAttributes.id == outputNumber.font_attribute) {
                    setFontFromAttribute(textView, fontAttributes);
                }
            }
        }
        String options = String.format("%4s", Integer.toBinaryString(outputNumber.options)).replace(' ', '0');
        if (options.substring(3, 4).equals("1")) {
            // Transparent
            textView.setBackgroundColor(Color.TRANSPARENT);
        }
        if (options.substring(2, 3).equals("1")) {
            // TODO: Leading Zeros
        }
        if (outputNumber.variable_reference > 0 && outputNumber.variable_reference < 65535) {
            // TODO: Variable Ref
        } else {
            float displayValue = (outputNumber.value + outputNumber.offset) * outputNumber.scale;
            textView.setText(String.valueOf(displayValue));
        }
        // TODO: Num of decimals & format
        // TODO: justification
        for (IncludeMacro includeMacro : outputNumber.include_macro) {
            for (Macro macro : objectPool.macro) {
                if (includeMacro.macro_id == macro.id) {
                    textView.setOnClickListener(setMacro(macro));
                }
            }
        }
        viewsList.add(textView);
    }

    private void createOutputList(OutputList outputList) {
        // TODO: Output List
    }

    private void createLine(Line line) {
        LineDrawable lineDrawable = new LineDrawable(context);
        lineDrawable.setId(line.id);
        viewsList.add(lineDrawable);
        // TODO: Line
    }

    private void createRectangle(ObjectPool objectPool, Rectangle rectangle) {
        LineAttributes lineAttribute = null;
        for (LineAttributes lineAttributes : objectPool.line_attribute) {
            if (lineAttributes.id == rectangle.line_attributes) {
                lineAttribute = lineAttributes;
            }
        }
        FillAttributes fillAttribute = null;
        for (FillAttributes fillAttributes : objectPool.fill_attribute) {
            if (fillAttributes.id == rectangle.fill_attributes) {
                fillAttribute = fillAttributes;
            }
        }
        RectangleDrawable rectangleDrawable = new RectangleDrawable(context, lineAttribute, fillAttribute);
        rectangleDrawable.setId(rectangle.id);
        viewsList.add(rectangleDrawable);
        // TODO: Rectangle
    }

    private void createEllipse(Ellipse ellipse) {
        // TODO: Ellipse
    }

    private void createPolygon(Polygon polygon) {
        // TODO: Polygon
    }

    private void createMeter(Meter meter) {
        // TODO: Meter
    }

    private void createLinearBarGraph(LinearBarGraph linearBarGraph) {
        // TODO: LinearBarGraph
    }

    private void createArchedBarGraph(ArchedBarGraph archedBarGraph) {
        // TODO: ArchedBarGraph
    }

    private void createPictureGraphic(PictureGraphic pictureGraphic) {
        // TODO: PicGraphic
    }

    private void createObjectPointer(ObjectPointer objectPointer) {
        // TODO: ObjectPtr
    }

    private void createDataMask(ObjectPool objectPool, DataMask dataMask) {
        DataMaskFragment dataMaskFragment = DataMaskFragment.newInstance()
                .setId(dataMask.id)
                .setBackgroundColor(dataMask.background_colour)
                .setSoftKeyMaskId(dataMask.soft_key_mask);

        if (dataMask.include_object != null) {
            for (IncludeObject includeObject : dataMask.include_object) {
                for (View container : viewsList) {
                    if (container.getId() == includeObject.id) {
                        RelativeLayout.LayoutParams paramsContainer = (RelativeLayout.LayoutParams) container.getLayoutParams();
                        // TODO: Not correct?? Double check
                        if (paramsContainer != null) {
                            paramsContainer.leftMargin = includeObject.pos_x;
                            paramsContainer.topMargin = includeObject.pos_y;
                            container.setLayoutParams(paramsContainer);
                        }
                        dataMaskFragment.addIncludeObject(container);
                    }
                }
            }
        }
        for (IncludeMacro includeMacro : dataMask.include_macro) {
            for (Macro macro : objectPool.macro) {
                if (includeMacro.macro_id == macro.id) {
                    // Self generated "Setter"
                    dataMaskFragment.setOnClickListener(setMacro(macro));
                }
            }
        }
        dataMaskFragmentList.add(dataMaskFragment);
    }

    private void createAlarmMask(AlarmMask alarmMask) {
        // TODO: AlarmMasks
    }

    // TODO: Use a fragment for container maybe??
    private void createContainer(ObjectPool objectPool, Container container) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setId(container.id);
        relativeLayout.setTag(container.id);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(Convert.convertDpToPixel(container.width, context), Convert.convertDpToPixel(container.height, context)));
        if (container.hidden == 0) {
            relativeLayout.setVisibility(View.VISIBLE);
        } else if (container.hidden == 1) {
            relativeLayout.setVisibility(View.INVISIBLE);
        }
        if (container.include_object != null) {
            for (IncludeObject includeObject : container.include_object) {
                for (View viewItem : viewsList) {
                    if (viewItem.getId() == includeObject.id) {
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewItem.getLayoutParams();
                        // TODO: does this move other items around? I believe so...
                        params.leftMargin = includeObject.pos_x;
                        params.topMargin = includeObject.pos_y;
                        viewItem.setLayoutParams(params);
                        // TODO: Not good programming!
                        // TODO: FIX THIS
                        if (((RelativeLayout) viewItem.getParent()) != null) {
                            ((RelativeLayout) viewItem.getParent()).removeView(viewItem);
                        }
                        relativeLayout.addView(viewItem);
                    }
                }
            }
        }
        for (IncludeMacro includeMacro : container.include_macro) {
            for (Macro macro : objectPool.macro) {
                if (includeMacro.macro_id == macro.id) {
                    relativeLayout.setOnClickListener(setMacro(macro));
                }
            }
        }
        viewsList.add(relativeLayout);
    }

    /**
     * Similar to a @Container Object, however only used within the defines of a Soft Key Mask Area
     *
     * @param softKeyMask The SoftKeyMask to define and Create
     */
    private void createSoftKeyMask(SoftKeyMask softKeyMask) {
        SoftKeyMaskFragment softKeyMaskFragment = SoftKeyMaskFragment.newInstance()
                .setId(softKeyMask.id)
                .setBackgroundColor(softKeyMask.background_colour);

        if (softKeyMask.include_object != null) {
            for (IncludeObject includeObject : softKeyMask.include_object) {
                for (View key : viewsList) {
                    if (key.getId() == includeObject.id) {
                        softKeyMaskFragment.addIncludeObject(key);
                    }
                }
            }
        }
        softKeyMaskFragmentList.add(softKeyMaskFragment);
    }

    /**
     * Font Attribute helper
     *
     * @param textView       The @TextView to be applied to.
     * @param fontAttributes The @FontAttributes to use on the TextView.
     */
    private void setFontFromAttribute(TextView textView, FontAttributes fontAttributes) {
        textView.setTextColor(context.getResources().getColor(context.getResources().getIdentifier("vt" + fontAttributes.font_colour, "color", context.getPackageName())));
        switch (fontAttributes.font_size) {
            case 0:
                textView.setTextSize(6);
                break;
            case 1:
                textView.setTextSize(8);
                break;
            case 2:
                textView.setTextSize(8);
                break;
            case 3:
                textView.setTextSize(12);
                break;
            case 4:
                textView.setTextSize(16);
                break;
            case 5:
                textView.setTextSize(16);
                break;
            case 6:
                textView.setTextSize(24);
                break;
            case 7:
                textView.setTextSize(32);
                break;
            case 8:
                textView.setTextSize(32);
                break;
            case 9:
                textView.setTextSize(48);
                break;
            case 10:
                textView.setTextSize(64);
                break;
            case 11:
                textView.setTextSize(64);
                break;
            case 12:
                textView.setTextSize(96);
                break;
            case 13:
                textView.setTextSize(128);
                break;
            case 14:
                textView.setTextSize(128);
                break;
            default:
                break;
        }
        // TODO: Type
        // TODO: Style
        // TODO: Macro?
    }

    /**
     * Sets the macro event for a given object.
     *
     * @param macro
     */
    private View.OnClickListener setMacro(final Macro macro) {
        View.OnClickListener command = null;
        if (macro != null && macro.command != null) {
            if (macro.command.command_type == 173) {
                command = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setDataMask(((ChangeActiveMask) macro.command).new_active_mask_object_id);
                    }
                };
            }
        }
        return command;
    }
}
