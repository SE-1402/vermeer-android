package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="command_change_numeric_value")
public class CommandChangeNumericValue {

    @Attribute
    public String object_id;

    @Attribute
    public String new_value;

}
