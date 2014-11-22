package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "macro")
public class Macro {

    @Attribute
    public int id;

    @Attribute
    public int number_bytes_follow;

    @Attribute
    public Command command;
}
