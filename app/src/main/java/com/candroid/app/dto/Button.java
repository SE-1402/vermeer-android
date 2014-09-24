package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="button")
public class Button {

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
    private String border_colour;

    @Attribute
    private int key_code;

    @Attribute
    private String options;

}
