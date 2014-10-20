package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="container")
public class Container {

    @Attribute
    public String name;

    @Attribute
    public int id;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public int hidden;

    @ElementList(name="include_object", required=false, inline=true)
    public ArrayList<IncludeObject> includeObjects;
}
