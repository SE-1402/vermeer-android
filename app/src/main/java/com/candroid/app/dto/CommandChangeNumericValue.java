package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="command_change_numeric_value")
public class CommandChangeNumericValue {

    @Attribute
    private String object_id;

    @Attribute
    private String new_value;

}
