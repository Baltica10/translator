package com.test.translator.exception;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final MediaType CONTENT_TYPE = MediaType.APPLICATION_JSON;

    @ExceptionHandler(value = {com.test.translator.exception.ValidationException.class})
    public ResponseEntity<CustomException> handleValidationException(com.test.translator.exception.ValidationException ex) {
        logException(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(CONTENT_TYPE)
                .body(new CustomException(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    private void logException(@NotNull Exception ex) {
        log.warn("Exception message: {}", ex.getMessage());
        log.debug("Trace: ", ex);
    }

    @Data
    @AllArgsConstructor
    private static class CustomException {
        private String message;
        private int status;
    }
}