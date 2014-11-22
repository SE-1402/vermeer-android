package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "input_string")
public class InputString {

    @Attribute
    public int id;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public int background_colour;

    @Attribute
    public int font_attributes;

    @Attribute
    public int input_attributes;

    @Attribute
    public int options;

    @Attribute
    public int variable_reference;

    @Attribute
    public int justification;

    @Attribute
    public int length;

    @Attribute
    public String value;

    @Attribute
    public int enabled;

    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;
}
