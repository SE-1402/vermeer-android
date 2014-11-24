package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "linear_bar_graph")
public class LinearBarGraph {

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private int colour;

    @Attribute
    private int target_line_colour;

    @Attribute
    private int options;

    @Attribute
    private int number_of_ticks;

    @Attribute
    private int min_value;

    @Attribute
    private int max_value;

    @Attribute
    private int variable_reference;

    @Attribute
    private int value;

    @Attribute
    private int target_value_variable_reference;

    @Attribute
    private int target_value;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
