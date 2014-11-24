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

    @ElementList(name = "include_object", inline = true)
    @SerializedName("include_object")
    public ArrayList<IncludeObject> include_object;

    @ElementList(name = "include_macro", inline = true)
    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;

}
