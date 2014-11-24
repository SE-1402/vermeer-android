package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="button")
public class Button {

    @Attribute
    public int id;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public int background_colour;

    @Attribute
    public int border_colour;

    @Attribute
    public int key_code;

    @Attribute
    public int options;

    @SerializedName("include_object")
    public ArrayList<IncludeObject> include_object;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;

}
