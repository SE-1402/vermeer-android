package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="working_set")
public class WorkingSet {

    @Attribute
    public int id;

    @Attribute
    public int background_colour;

    @Attribute
    public boolean selectable;

    @Attribute
    public int active_mask;

    @Attribute
    public String name;

    @Element(name="include_object")
    @SerializedName("include_object")
    public ArrayList<IncludeObject> include_object;

    @Element(name="include_macro")
    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;

    @ElementList(name="include_language", inline=true)
    @SerializedName("include_language")
    public ArrayList<Language> include_language;

}