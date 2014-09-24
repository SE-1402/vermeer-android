package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="objectpointer")
public class ObjectPointer {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private String value;
}
