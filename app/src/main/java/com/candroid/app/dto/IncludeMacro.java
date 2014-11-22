package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "include_macro")
public class IncludeMacro {

    @Attribute
    public int event_id;

    @Attribute
    public int macro_id;
}
