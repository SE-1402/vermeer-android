package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "rectangle")
public class Line {

    @Attribute
    private int id;

    @Attribute
    private int line_attributes;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private int line_direction;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
