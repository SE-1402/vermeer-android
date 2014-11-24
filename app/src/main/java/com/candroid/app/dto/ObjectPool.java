package com.candroid.app.dto;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root
public class ObjectPool {

    @Element
    @SerializedName("working_set")
    public WorkingSet working_set;

    @ElementList(entry = "data_mask", inline = true)
    @SerializedName("data_mask")
    public ArrayList<DataMask> data_mask;

    @ElementList(entry = "alarm_mask", inline = true)
    @SerializedName("alarm_mask")
    public ArrayList<AlarmMask> alarm_mask;

    @ElementList(entry = "container", inline = true)
    @SerializedName("container")
    public ArrayList<Container> container;

    @ElementList(entry = "soft_key_mask", inline = true)
    @SerializedName("soft_key_mask")
    public ArrayList<SoftKeyMask> soft_key_mask;

    @ElementList(entry = "key", inline = true)
    @SerializedName("key")
    public ArrayList<Key> key;

    @ElementList(entry = "button", inline = true)
    @SerializedName("button")
    public ArrayList<Button> button;

    @ElementList(entry = "input_boolean", inline = true)
    @SerializedName("input_boolean")
    public ArrayList<InputBoolean> input_boolean;

    @ElementList(entry = "input_string", inline = true)
    @SerializedName("input_string")
    public ArrayList<InputString> input_string;

    @ElementList(entry = "input_number", inline = true)
    @SerializedName("input_number")
    public ArrayList<InputNumber> input_number;

    @ElementList(entry = "input_list", inline = true)
    @SerializedName("input_list")
    public ArrayList<InputList> input_list;

    @ElementList(entry = "output_string", inline = true)
    @SerializedName("output_string")
    public ArrayList<OutputString> output_string;

    @ElementList(entry = "output_number", inline = true)
    @SerializedName("output_number")
    public ArrayList<OutputNumber> output_number;

    @ElementList(entry = "output_list", inline = true)
    @SerializedName("output_list")
    public ArrayList<OutputList> output_list;

    @ElementList(entry = "line", inline = true)
    @SerializedName("line")
    public ArrayList<Line> line;

    @ElementList(entry = "rectangle", inline = true)
    @SerializedName("rectangle")
    public ArrayList<Rectangle> rectangle;

    @ElementList(entry = "ellipse", inline = true)
    @SerializedName("ellipse")
    public ArrayList<Ellipse> ellipse;

    @ElementList(entry = "polygon", inline = true)
    @SerializedName("polygon")
    public ArrayList<Polygon> polygon;

    @ElementList(entry = "meter", inline = true)
    @SerializedName("meter")
    public ArrayList<Meter> meter;

    @ElementList(entry = "linear_bar_graph", inline = true)
    @SerializedName("linear_bar_graph")
    public ArrayList<LinearBarGraph> linear_bar_graph;

    @ElementList(entry = "arched_bar_graph", inline = true)
    @SerializedName("arched_bar_graph")
    public ArrayList<ArchedBarGraph> arched_bar_graph;

    @ElementList(entry = "picture_graphic", inline = true)
    @SerializedName("picture_graphic")
    public ArrayList<PictureGraphic> picture_graphic;

    @ElementList(entry = "number_variable", inline = true)
    @SerializedName("number_variable")
    public ArrayList<NumberVariable> number_variable;

    @ElementList(entry = "string_variable", inline = true)
    @SerializedName("string_variable")
    public ArrayList<StringVariable> string_variable;

    @ElementList(entry = "font_attribute", inline = true)
    @SerializedName("font_attribute")
    public ArrayList<FontAttributes> font_attribute;

    @ElementList(entry = "line_attribute", inline = true)
    @SerializedName("line_attribute")
    public ArrayList<LineAttributes> line_attribute;

    @ElementList(entry = "fill_attribute", inline = true)
    @SerializedName("fill_attribute")
    public ArrayList<FillAttributes> fill_attribute;

    @ElementList(entry = "input_attribute", inline = true)
    @SerializedName("input_attribute")
    public ArrayList<InputAttributes> input_attribute;

    @ElementList(entry = "object_pointer", inline = true)
    @SerializedName("object_pointer")
    public ArrayList<ObjectPointer> object_pointer;

    @ElementList(entry = "include_macro", inline = true)
    @SerializedName("include_macro")
    public ArrayList<IncludeMacro> include_macro;

    public ObjectPool() {

    }
}
