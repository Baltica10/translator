package com.test.translator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends CommonException {

    public ValidationException(String message) {
        super(message, null);
    }
}