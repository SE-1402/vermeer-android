package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="linearbargraph")
public class LinearBarGraph {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private int min_value;

    @Attribute
    private int max_value;

    @Attribute
    private String colour;

    @Attribute
    private String target_line_colour;

    @Attribute
    private String options;

    @Attribute
    private int number_of_ticks;

    @Attribute
    private int value;

    @Attribute
    private String variable_reference;

    @Attribute
    private int target_value;

    @Attribute
    private String target_value_variable_reference;
}
