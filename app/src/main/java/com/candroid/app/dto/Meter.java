package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "meter")
public class Meter {

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int needle_colour;

    @Attribute
    private int border_colour;

    @Attribute
    private int arc_and_tick_colour;

    @Attribute
    private int options;

    @Attribute
    private int number_of_ticks;

    @Attribute
    private int start_angle;

    @Attribute
    private int end_angle;

    @Attribute
    private int min_value;

    @Attribute
    private int max_value;

    @Attribute
    private int variable_reference;

    @Attribute
    private int value;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
