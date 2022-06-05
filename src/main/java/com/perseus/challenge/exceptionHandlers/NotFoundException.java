package com.perseus.challenge.exceptionHandlers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends  RuntimeException {
    private final String NOT_FOUND_CODE = "404";
    private String code;

    public NotFoundException(String message) {
        super(message);
        this.code = this.NOT_FOUND_CODE;
    }

}

