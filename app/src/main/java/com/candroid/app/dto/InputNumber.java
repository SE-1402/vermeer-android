package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="inputnumber")
public class InputNumber {

    @Attribute
    public String name;

    @Attribute
    public int id;

    @Attribute
    public int width;

    @Attribute
    public int height;

    @Attribute
    public String background_colour;

    @Attribute
    public String font_attributes;

    @Attribute
    public String options;

    @Attribute
    public int min_value;

    @Attribute
    public int max_value;

    @Attribute
    public int offset;

    @Attribute
    public int scale;

    @Attribute
    public int number_of_decimals;

    @Attribute
    public String format;

    @Attribute
    public String horizontal_justification;

    @Attribute
    public String vertical_justification;

    @Attribute
    public String inputobject_options;

    @Attribute
    public int value;

    @Attribute
    public String variable_reference;
}
