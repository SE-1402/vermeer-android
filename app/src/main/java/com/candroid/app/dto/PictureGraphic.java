package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "picture_graphic")
public class PictureGraphic {

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int actual_width;

    @Attribute
    private int actual_height;

    @Attribute
    private int options;

    @Attribute
    private int format;

    @Attribute
    private int transparency_colour;

    @Attribute(required = false)
    private int number_of_bytes;

    @SerializedName("raw_data")
    public ArrayList<Integer> raw_data;

    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;

}
