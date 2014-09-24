package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="archedbargraph")
public class ArchedBarGraph {
    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int bar_graph_width;

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
    private int start_angle;

    @Attribute
    private int end_angle;

    @Attribute
    private int value;

    @Attribute
    private String variable_reference;

    @Attribute
    private int target_value;

    @Attribute
    private String target_value_variable_reference;

}
