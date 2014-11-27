package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "font_attribute")
public class FontAttributes {

    @Attribute
    public int id;

    @Attribute
    public int font_colour;

    @Attribute
    public int font_size;

    @Attribute
    public int font_type;

    @Attribute
    public int font_style;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
