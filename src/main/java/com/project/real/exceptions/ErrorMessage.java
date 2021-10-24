package com.project.real.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorMessage {
    @JsonProperty("description")
    private String message;

    public ErrorMessage(String message) {

    }
}
