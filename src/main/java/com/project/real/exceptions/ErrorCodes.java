package com.project.real.exceptions;

import java.io.Serializable;




public enum ErrorCodes implements Serializable {
    GENERAL,
    UNAUTHORIZED,
    RESOURCE_NOT_FOUND,
    SESSION_TIME_OUT;

    private ErrorCodes() {
    }
}
