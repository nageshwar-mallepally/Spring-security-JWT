package com.project.real.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.project.real.model.enums.ObjectType;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public class ObjectTypeDeserializer extends JsonDeserializer<Enum> {
    @Override
    @Transactional
    public ObjectType deserialize(JsonParser jsonparser,
                                  DeserializationContext deserializationcontext) throws IOException,
            JsonProcessingException {
        return ObjectType.valueOf(jsonparser.getText());
    }

}