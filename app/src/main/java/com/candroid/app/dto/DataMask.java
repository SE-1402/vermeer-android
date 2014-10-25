package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="datamask")
public class DataMask {

    @Attribute
    public String name;

    @Attribute
    public int id;

    @Attribute
    public String background_colour;

    @Attribute
    public String soft_key_mask;

    @ElementList(name="include_object", required=false, inline=true)
    @SerializedName("include_object")
    public ArrayList<IncludeObject> includeObject;

}
