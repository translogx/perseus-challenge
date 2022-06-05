package com.perseus.challenge.exceptionHandlers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerRuntimeException extends  RuntimeException {
    private final String RUNTIME_EXCEPTION = "500";
    private String code;

    public ServerRuntimeException(String message) {
        super(message);
        this.code = RUNTIME_EXCEPTION;
    }

}
