package com.candroid.app.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="command_change_active_mask")
public class CommandChangeActiveMask {

    @Attribute
    public String working_set_object_id;

    @Attribute
    public String new_active_mask_object_id;

}
