package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "input_boolean")
public class InputBoolean {

    @Attribute
    public int id;

    @Attribute
    public int background_colour;

    @Attribute
    public int width;

    @Attribute
    public int foreground_color;

    @Attribute
    public int variable_reference;

    @Attribute
    public int enabled;

    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;
}
