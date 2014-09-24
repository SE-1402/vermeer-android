package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="numbervariable")
public class NumberVariable {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int value;

}
