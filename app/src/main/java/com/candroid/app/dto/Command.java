package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "command")
public class Command {

    @Attribute
    public int id;
}
