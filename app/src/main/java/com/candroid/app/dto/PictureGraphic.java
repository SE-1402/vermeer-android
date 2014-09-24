package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="picturegraphic")
public class PictureGraphic {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private int width;

    @Attribute(required=false)
    private String height;

    @Attribute
    private String options;

    @Attribute
    private String transparency_colour;

    @Attribute
    private String file1;

    @Attribute
    private String file4;

    @Attribute
    private String file8;

    @Attribute
    private int abs_path1;

    @Attribute
    private int abs_path4;

    @Attribute
    private int abs_path8;

    @Attribute(required=false)
    private String number_of_bytes;

}
