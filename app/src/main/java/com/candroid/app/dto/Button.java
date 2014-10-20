package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="button")
public class Button {

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
    public String border_colour;

    @Attribute
    public int key_code;

    @Attribute
    public String options;

}
