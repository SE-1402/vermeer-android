package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="object_pointer")
public class ObjectPointer {

    @Attribute
    private int id;

    @Attribute
    private int value;
}
