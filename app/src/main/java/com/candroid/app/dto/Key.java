package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="key")
public class Key {

    @Attribute
    public int id;

    @Attribute
    public int background_colour;

    @Attribute
    public int key_code;

    @ElementList(name="include_object", required=false, inline=true)
    @SerializedName("include_object")
    public ArrayList<IncludeObject> include_object;

    @ElementList(name="include_macro", required=false, inline=true)
    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;
}
