package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root
public class OutputNumber {

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
    public int options;

    @Attribute
    public int variable_reference;

    @Attribute
    public int value;

    @Attribute
    public int offset;

    @Attribute
    public float scale;

    @Attribute
    public int number_of_decimals;

    @Attribute
    public int format;

    @Attribute
    public int justification;

    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;
}
