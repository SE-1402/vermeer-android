package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="workingset")
public class WorkingSet {

    @Attribute
    private String name;

    @Attribute
    private int id;

    @Attribute
    private String background_colour;

    @Attribute
    private int selectable;

    @Attribute
    private String active_mask;

    @Element(name="outputstring")
    private OutputString outputstring;

    @ElementList(name="language", inline=true)
    private ArrayList<Language> languages;

}