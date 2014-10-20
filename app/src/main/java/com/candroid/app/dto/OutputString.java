package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="outputstring")
public class OutputString {

    @Attribute
    public String name;

    @Attribute
    public int id;

    @Attribute(required=false)
    public String language;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public String font_attributes;

    @Attribute
    public String background_colour;

    @Attribute
    public String options;

    @Attribute
    public String horizontal_justification;

    @Attribute
    public String vertical_justification;

    @Attribute
    public String value;

    @Attribute
    public int length;

    @Attribute
    public int auto_set_length;

    @Attribute
    public String variable_reference;

    @Attribute(required=false)
    public int pos_x;

    @Attribute(required=false)
    public int pos_y;
}
