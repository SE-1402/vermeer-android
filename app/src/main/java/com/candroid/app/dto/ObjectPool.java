package com.candroid.app.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root
public class ObjectPool {

    @Element
    public WorkingSet workingset;

    @ElementList(entry="macro", inline=true)
    public ArrayList<Macro> macros;

    @ElementList(entry="datamask", inline=true)
    public ArrayList<DataMask> dataMasks;

    @ElementList(entry="container", inline=true)
    public ArrayList<Container> containers;

    @ElementList(entry="key", inline=true)
    public ArrayList<Key> keys;

    @ElementList(entry="button", inline=true)
    public ArrayList<Button> buttons;

    @ElementList(entry="inputnumber", inline=true)
    public ArrayList<InputNumber> inputNumbers;

    @ElementList(entry="outputnumber", inline=true)
    public ArrayList<OutputNumber> outputNumbers;

    @ElementList(entry="rectangle", inline=true)
    public ArrayList<Rectangle> rectangles;

    @ElementList(entry="meter", inline=true)
    public ArrayList<Meter> meters;

    @ElementList(entry="archedbargraph", inline=true)
    public ArrayList<ArchedBarGraph> archedBarGraphs;

    @ElementList(entry="picturegraphic", inline=true)
    public ArrayList<PictureGraphic> pictureGraphics;

    @ElementList(entry="numbervariable", inline=true)
    public ArrayList<NumberVariable> numberVariables;

    @ElementList(entry="lineattributes", inline=true)
    public ArrayList<LineAttributes> lineAttributeses;

    @ElementList(entry="inputattributes", inline=true)
    public ArrayList<InputAttributes> inputAttributeses;

    @ElementList(entry="outputstring", inline=true)
    public ArrayList<OutputString> outputStrings;

    @ElementList(entry="objectpointer", inline=true)
    public ArrayList<ObjectPointer> objectPointers;

    @ElementList(entry="ellipse", inline=true)
    public ArrayList<Ellipse> ellipses;

    @ElementList(entry="linearbargraph", inline=true)
    public ArrayList<LinearBarGraph> linearBarGraphs;

    @ElementList(entry="softkeymask", inline=true)
    public ArrayList<SoftKeyMask> softKeyMasks;

    public ObjectPool(){

    };
}
