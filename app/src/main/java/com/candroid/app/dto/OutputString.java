package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="outputstring")
public class OutputString {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute(required=false)
    private String language;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private String font_attributes;

    @Attribute
    private String background_colour;

    @Attribute
    private String options;

    @Attribute
    private String horizontal_justification;

    @Attribute
    private String vertical_justification;

    @Attribute
    private String value;

    @Attribute
    private int length;

    @Attribute
    private int auto_set_length;

    @Attribute
    private String variable_reference;

    @Attribute(required=false)
    private int pos_x;

    @Attribute(required=false)
    private int pos_y;
}
