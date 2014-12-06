package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "line_attribute")
public class LineAttributes {

    @Attribute
    public int id;

    @Attribute
    public int line_colour;

    @Attribute
    public int line_width;

    @Attribute
    public int line_art;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
