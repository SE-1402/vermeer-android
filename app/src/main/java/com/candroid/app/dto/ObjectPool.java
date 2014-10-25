package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root
public class ObjectPool {

    @Element
    @SerializedName("workingset")
    public WorkingSet workingset;

    @ElementList(entry="macro", inline=true)
    @SerializedName("macro")
    public ArrayList<Macro> macro;

    @ElementList(entry="datamask", inline=true)
    @SerializedName("datamask")
    public ArrayList<DataMask> dataMask;

    @ElementList(entry="container", inline=true)
    @SerializedName("container")
    public ArrayList<Container> container;

    @ElementList(entry="key", inline=true)
    @SerializedName("key")
    public ArrayList<Key> key;

    @ElementList(entry="button", inline=true)
    @SerializedName("button")
    public ArrayList<Button> button;

    @ElementList(entry="inputnumber", inline=true)
    @SerializedName("inputnumber")
    public ArrayList<InputNumber> inputNumber;

    @ElementList(entry="outputnumber", inline=true)
    @SerializedName("outputnumber")
    public ArrayList<OutputNumber> outputNumber;

    @ElementList(entry="rectangle", inline=true)
    @SerializedName("rectangle")
    public ArrayList<Rectangle> rectangle;

    @ElementList(entry="meter", inline=true)
    @SerializedName("meter")
    public ArrayList<Meter> meter;

    @ElementList(entry="archedbargraph", inline=true)
    @SerializedName("archedbargraph")
    public ArrayList<ArchedBarGraph> archedBarGraph;

    @ElementList(entry="picturegraphic", inline=true)
    @SerializedName("picturegraphic")
    public ArrayList<PictureGraphic> pictureGraphic;

    @ElementList(entry="numbervariable", inline=true)
    @SerializedName("numbervariable")
    public ArrayList<NumberVariable> numberVariable;

    @ElementList(entry="lineattributes", inline=true)
    @SerializedName("lineattributes")
    public ArrayList<LineAttributes> lineAttributese;

    @ElementList(entry="inputattributes", inline=true)
    @SerializedName("inputattributes")
    public ArrayList<InputAttributes> inputAttributese;

    @ElementList(entry="outputstring", inline=true)
    @SerializedName("outputstring")
    public ArrayList<OutputString> outputString;

    @ElementList(entry="objectpointer", inline=true)
    @SerializedName("objectpointer")
    public ArrayList<ObjectPointer> objectPointer;

    @ElementList(entry="ellipse", inline=true)
    @SerializedName("ellipse")
    public ArrayList<Ellipse> ellipse;

    @ElementList(entry="linearbargraph", inline=true)
    @SerializedName("linearbargraph")
    public ArrayList<LinearBarGraph> linearBarGraph;

    @ElementList(entry="softkeymask", inline=true)
    @SerializedName("softkeymask")
    public ArrayList<SoftKeyMask> softKeyMask;

    public ObjectPool(){

    };
}
