package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="ellipse")
public class Ellipse {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute
    private int height;

    @Attribute
    private String line_attributes;

    @Attribute
    private String fill_attributes;

    @Attribute
    private String ellipse_type;

    @Attribute
    private int start_angle;

    @Attribute
    private int end_angle;
}
