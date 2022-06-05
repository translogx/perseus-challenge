package com.perseus.challenge.exceptionHandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiAdvice  {
    @ExceptionHandler(RequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseExceptionResponse handleBadRequestException(RequestException e) {
        log.error("RequestException", e);
        return new BaseExceptionResponse(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ServerRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseExceptionResponse handleServerRuntimeException(ServerRuntimeException e) {
        log.error("ServerRuntimeException", e);
        return new BaseExceptionResponse(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseExceptionResponse handleNootFoundException(NotFoundException e) {
        log.error("NotFoundException", e);
        return new BaseExceptionResponse(e.getCode(), e.getMessage());
    }
}
