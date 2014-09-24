package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="lineattributes")
public class LineAttributes {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private String line_art;

    @Attribute
    private String line_colour;

    @Attribute
    private int line_width;
}
