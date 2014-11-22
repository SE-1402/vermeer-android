package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "polygon")
public class Polygon {

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private int line_attributes;

    @Attribute
    private int fill_attributes;

    @Attribute
    private int polygon_type;

    @SerializedName("include_points")
    public ArrayList<Point> include_points;

    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;

}
