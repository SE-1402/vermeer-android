package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "string_variable")
public class StringVariable {

    @Attribute
    public int id;

    @Attribute
    public int length;

    @Attribute
    public String value;

}
