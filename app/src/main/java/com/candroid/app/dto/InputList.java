package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "input_list")
public class InputList {

    @Attribute
    public int id;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public int variable_reference;

    @Attribute
    public int value;

    @Attribute
    public int number_of_list_items;

    @Attribute
    public int options;

    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
