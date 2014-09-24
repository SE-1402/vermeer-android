package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="container")
public class Container {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private int hidden;

    @ElementList(name="include_object", required=false, inline=true)
    private ArrayList<IncludeObject> includeObjects;
}
