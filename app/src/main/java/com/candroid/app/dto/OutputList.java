package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "output_list")
public class OutputList {

    @Attribute
    public int id;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public int variable_reference;

    @Attribute
    public String value;

    @SerializedName("include_list")
    public ArrayList<ListItem> include_list;

    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;

}
