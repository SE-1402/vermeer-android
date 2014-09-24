package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="rectangle")
public class Rectangle {

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
    private String line_suppression;

}
