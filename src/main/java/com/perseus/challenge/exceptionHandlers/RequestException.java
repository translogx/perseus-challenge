package com.perseus.challenge.exceptionHandlers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestException extends  RuntimeException {
    private final String BAD_REQUEST_CODE = "400";
    private String code;

    public RequestException(String message) {
        super(message);
        this.code = BAD_REQUEST_CODE;
    }

}

