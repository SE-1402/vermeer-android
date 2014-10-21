package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="macro")
public class Macro {

    @Attribute
    public String name;

    @Attribute
    public int id;

    @Attribute
    public int number_of_bytes;

    @Element(name="command_change_numeric_value", required = false)
    public CommandChangeNumericValue command_change_numeric_value;

    @Element(name="command_change_active_mask", required = false)
    public CommandChangeActiveMask command_change_active_mask;
}
