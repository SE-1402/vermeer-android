package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "output_string")
public class OutputString {

    @Attribute
    public int id;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public int background_colour;

    @Attribute
    public int font_attribute;

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

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;

}
