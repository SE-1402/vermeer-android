package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="key")
public class Key {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private String background_colour;

    @Attribute
    private int key_code;

    @ElementList(name="include_macro", required=false, inline=true)
    private ArrayList<IncludeMacro> includeMacros;

    @ElementList(name="include_object", required=false, inline=true)
    private ArrayList<IncludeObject> includeObjects;
}
