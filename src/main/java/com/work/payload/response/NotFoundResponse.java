package com.work.payload.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundResponse extends RuntimeException {

    public NotFoundResponse(String message) {
        super(message);
    }

    public NotFoundResponse(String message, Throwable cause) {
        super(message, cause);
    }
}