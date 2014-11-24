package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "input_attribute")
public class InputAttributes {

    @Attribute
    private int id;

    @Attribute
    private int validation_type;

    @Attribute
    private int length;

    @Attribute
    private String validation_string;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
