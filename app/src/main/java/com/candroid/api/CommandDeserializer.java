package com.candroid.api;

import com.candroid.app.dto.commands.ChangeActiveMask;
import com.candroid.app.dto.commands.Command;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CommandDeserializer implements JsonDeserializer<Command> {
    @Override
    public Command deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) jsonElement;
        if (jsonObject != null && jsonObject.get("command_type") != null) {
            if (jsonObject.get("command_type").getAsInt() == 173) {
                return new ChangeActiveMask(
                        jsonObject.get("working_set_object_id").getAsInt(),
                        jsonObject.get("new_active_mask_object_id").getAsInt(),
                        jsonObject.get("reserved").getAsInt()
                );
            }
        }
        return null;
    }
}
