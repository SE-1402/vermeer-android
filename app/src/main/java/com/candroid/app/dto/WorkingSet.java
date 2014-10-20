package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="workingset")
public class WorkingSet {

    @Attribute
    public String name;

    @Attribute
    public int id;

    @Attribute
    public String background_colour;

    @Attribute
    public int selectable;

    @Attribute
    public String active_mask;

    @Element(name="outputstring")
    public OutputString outputstring;

    @ElementList(name="language", inline=true)
    public ArrayList<Language> languages;

}