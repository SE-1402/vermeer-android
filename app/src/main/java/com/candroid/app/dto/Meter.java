package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="meter")
public class Meter {
    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute(required=false)
    private int height;

    @Attribute
    private int min_value;

    @Attribute
    private int max_value;

    @Attribute
    private String needle_colour;

    @Attribute
    private String border_colour;

    @Attribute
    private String arc_and_tick_colour;

    @Attribute
    private int number_of_ticks;

    @Attribute
    private String options;

    @Attribute
    private int start_angle;

    @Attribute
    private int end_angle;

    @Attribute
    private int value;

    @Attribute
    private String variable_reference;

}
