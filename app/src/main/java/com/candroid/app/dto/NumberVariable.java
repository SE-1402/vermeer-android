package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="number_variable")
public class NumberVariable {

    @Attribute
    public int id;

    @Attribute
    public int value;

}
