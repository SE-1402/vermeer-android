package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="include_macro")
public class IncludeMacro {

    @Attribute
    private String name;

    @Attribute
    private String event;

}
