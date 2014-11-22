package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "soft_key_mask")
public class SoftKeyMask {

    @Attribute
    public int id;

    @Attribute
    public int background_colour;

    @ElementList(name = "include_objects", inline = true)
    @SerializedName("include_objects")
    public ArrayList<IncludeObject> include_Include_objects;

    @ElementList(name = "include_macros", inline = true)
    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;

}
