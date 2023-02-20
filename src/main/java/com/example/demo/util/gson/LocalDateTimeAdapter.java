package com.example.demo.util.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc,
                                 JsonSerializationContext context) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String format = src.format(formatter);
        return new JsonPrimitive(format);
    }

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement != null) {
            String elementAsString = jsonElement.getAsString();
            if (elementAsString == null || "".equals(elementAsString.trim())) {
                return null;
            }
            return LocalDateTime.parse(elementAsString, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        }
        return null;
    }
}
