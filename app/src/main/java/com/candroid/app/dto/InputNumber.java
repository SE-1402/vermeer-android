package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="inputnumber")
public class InputNumber {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private String background_colour;

    @Attribute
    private String font_attributes;

    @Attribute
    private String options;

    @Attribute
    private int min_value;

    @Attribute
    private int max_value;

    @Attribute
    private int offset;

    @Attribute
    private int scale;

    @Attribute
    private int number_of_decimals;

    @Attribute
    private String format;

    @Attribute
    private String horizontal_justification;

    @Attribute
    private String vertical_justification;

    @Attribute
    private String inputobject_options;

    @Attribute
    private int value;

    @Attribute
    private String variable_reference;
}
