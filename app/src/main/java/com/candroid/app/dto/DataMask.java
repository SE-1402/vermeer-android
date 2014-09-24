package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="datamask")
public class DataMask {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private String background_colour;

    @Attribute
    private String soft_key_mask;

    @ElementList(name="include_object", required=false, inline=true)
    private ArrayList<IncludeObject> includeObjects;

}
