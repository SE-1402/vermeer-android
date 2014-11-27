package com.candroid.app.dto.commands;

public class ChangeActiveMask extends Command {

    public int working_set_object_id;

    public int new_active_mask_object_id;

    public int reserved;

    public ChangeActiveMask(int working_set_object_id, int new_active_mask_object_id, int reserved) {
        this.working_set_object_id = working_set_object_id;
        this.new_active_mask_object_id = new_active_mask_object_id;
        this.reserved = reserved;
    }

}
