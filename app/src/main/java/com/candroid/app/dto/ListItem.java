package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "include_object")
public class ListItem {

    @Attribute
    public int id;
}
