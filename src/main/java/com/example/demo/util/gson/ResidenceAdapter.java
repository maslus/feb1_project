package com.example.demo.util.gson;

import com.example.demo.external_system.spimex.core.Residence;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ResidenceAdapter implements JsonSerializer<Residence>, JsonDeserializer<Residence> {

    @Override
    public JsonElement serialize(Residence src, Type typeOfSrc,
                                 JsonSerializationContext context) {

        return new JsonPrimitive(src.getResidenceName());
    }

    @Override
    public Residence deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement != null) {
            String residenceName = jsonElement.getAsString();
            return Residence.valOf(residenceName);
        }
        return null;
    }
}
