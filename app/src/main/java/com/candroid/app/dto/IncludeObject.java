package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="include_object")
public class IncludeObject {

    @Attribute
    private String name;

    @Attribute(required=false)
    private int pos_x;

    @Attribute(required=false)
    private int pos_y;

}
