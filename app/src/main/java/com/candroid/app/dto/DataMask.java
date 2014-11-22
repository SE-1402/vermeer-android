package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="data_mask")
public class DataMask {

    @Attribute
    public int id;

    @Attribute
    public int background_colour;

    @Attribute
    public int soft_key_mask;

    @ElementList(name="include_objects", required=false, inline=true)
    @SerializedName("include_objects")
    public ArrayList<IncludeObject> include_Include_objects;

    @ElementList(name="include_macros", required=false, inline=true)
    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;

}
