package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="inputattributes")
public class InputAttributes {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private String validation_type;

    @Attribute
    private String validation_string;

    @Attribute
    private int length;

    @Attribute
    private int auto_set_length;
}
