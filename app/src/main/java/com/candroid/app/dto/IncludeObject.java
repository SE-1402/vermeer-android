package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="include_object")
public class IncludeObject {

    @Attribute
    public String name;

    @Attribute(required=false)
    public int pos_x;

    @Attribute(required=false)
    public int pos_y;

}
