package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="softkeymask")
public class SoftKeyMask {

    @Attribute
    public String name;

    @Attribute
    public int id;

    @Attribute
    public int background_colour;

    @ElementList(name="include_object", inline=true)
    public ArrayList<IncludeObject> includeObjects;
}
