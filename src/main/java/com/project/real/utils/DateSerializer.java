package com.project.real.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends JsonSerializer<Date> {
    public static final String DATEFORMAT = "dd/MM/yyyy, HH:mm:ss";

    public DateSerializer() {
    }

    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
        String df = "dd/MM/yyyy, HH:mm:ss";
        if(System.getProperty("cassini.timestamp.format") != null) {
            df = System.getProperty("cassini.timestamp.format");
        } else if(System.getProperty("emm.timestamp.format") != null) {
            df = System.getProperty("emm.timestamp.format");
        }

        SimpleDateFormat formatter = new SimpleDateFormat(df);
        String formattedDate = formatter.format(value);
        gen.writeString(formattedDate);
    }
}
