package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "fill_attributes")
public class FillAttributes {

    @Attribute
    private int id;

    @Attribute
    private int fill_type;

    @Attribute
    private int fill_colour;

    @Attribute
    private int font_pattern;

    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;
}
