package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="container")
public class Container {

    @Attribute
    public int id;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public int hidden;

    @ElementList(name="include_objects", required=false, inline=true)
    @SerializedName("include_objects")
    public ArrayList<IncludeObject> include_Include_objects;

    @ElementList(name="include_macros", required=false, inline=true)
    @SerializedName("include_macros")
    public ArrayList<IncludeObject> include_macros;
}
