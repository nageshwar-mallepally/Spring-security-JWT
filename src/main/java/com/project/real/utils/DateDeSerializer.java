package com.project.real.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateDeSerializer extends JsonDeserializer<Date> {
    public static final String DATEFORMAT = "dd/MM/yyyy, HH:mm:ss";

    public DateDeSerializer() {
    }

    public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {
        String df = "dd/MM/yyyy, HH:mm:ss";
        if(System.getProperty("cassini.timestamp.format") != null) {
            df = System.getProperty("cassini.timestamp.format");
        } else if(System.getProperty("emm.timestamp.format") != null) {
            df = System.getProperty("emm.timestamp.format");
        }

        SimpleDateFormat format = new SimpleDateFormat(df);
        String date = jsonparser.getText();

        try {
            return format.parse(date);
        } catch (ParseException var7) {
            throw new RuntimeException(var7);
        }
    }
}
