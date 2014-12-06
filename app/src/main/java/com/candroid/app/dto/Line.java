package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "rectangle")
public class Line {

    @Attribute
    public int id;

    @Attribute
    public int line_attributes;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public int line_direction;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
