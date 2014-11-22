package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="alarm_mask")
public class AlarmMask {

    @Attribute
    public int id;

    @Attribute
    public int background_colour;

    @Attribute
    public int soft_key_mask;

    @Attribute
    public int priority;

    @Attribute
    public int acoustic_signal;

    @ElementList(name="include_object", inline=true)
    @SerializedName("include_object")
    public ArrayList<IncludeObject> include_Include_object;

    @ElementList(name="include_macros", inline=true)
    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;

}
