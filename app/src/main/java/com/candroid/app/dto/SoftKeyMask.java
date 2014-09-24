package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="softkeymask")
public class SoftKeyMask {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int background_colour;

    @ElementList(name="include_object", inline=true)
    private ArrayList<IncludeObject> includeObjects;
}
