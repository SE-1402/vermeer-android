package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="key")
public class Key {

    @Attribute
    public String name;

    @Attribute
    public int id;

    @Attribute
    public String background_colour;

    @Attribute
    public int key_code;

    @ElementList(name="include_macro", required=false, inline=true)
    public IncludeMacro include_macro;

    @ElementList(name="include_object", required=false, inline=true)
    public IncludeObject include_object;
}
