package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="language")
public class Language {

    @Attribute
    private String code;

}
