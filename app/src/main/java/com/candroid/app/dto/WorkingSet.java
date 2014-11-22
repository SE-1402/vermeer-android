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

    @Element(name="include_objects")
    @SerializedName("include_objects")
    public ArrayList<IncludeObject> include_Include_objects;

    @Element(name="include_macros")
    @SerializedName("include_macros")
    public ArrayList<IncludeMacro> include_Include_macros;

    @ElementList(name="include_languages", inline=true)
    @SerializedName("include_languages")
    public ArrayList<Language> include_languages;

}