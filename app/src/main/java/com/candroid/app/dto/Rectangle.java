package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "rectangle")
public class Rectangle {

    @Attribute
    private int id;

    @Attribute
    private int line_attributes;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private int line_suppression;

    @Attribute
    private int fill_attributes;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;

}
