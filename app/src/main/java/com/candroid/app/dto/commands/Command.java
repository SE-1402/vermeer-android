package com.candroid.app.dto.commands;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "command")
public abstract class Command {

    @Attribute
    public int command_type;
}
