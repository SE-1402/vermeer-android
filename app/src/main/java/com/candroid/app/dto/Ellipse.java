package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "ellipse")
public class Ellipse {

    @Attribute
    private int id;

    @Attribute
    private String line_attributes;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private int ellipse_type;

    @Attribute
    private int start_angle;

    @Attribute
    private int end_angle;

    @Attribute
    private int fill_attributes;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
