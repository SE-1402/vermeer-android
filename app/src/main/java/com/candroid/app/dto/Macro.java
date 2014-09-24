package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="macro")
public class Macro {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int number_of_bytes;

    @Element(name="command_change_numeric_value", required = false)
    private CommandChangeNumericValue commandChangeNumericValue;

    @Element(name="command_change_active_mask", required = false)
    private CommandChangeActiveMask commandChangeActiveMask;
}
