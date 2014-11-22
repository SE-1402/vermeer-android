package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "string_variable")
public class StringVariable {

    @Attribute
    private int id;

    @Attribute
    private int length;

    @Attribute
    private String value;

}
