package com.endava.demo.controller;

import com.endava.demo.exception.TUIException;
import com.endava.demo.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

/**
 * Base controller which serves common functionalities and handling exceptions
 */
@ControllerAdvice
public class BaseController {


    /**
     * This is exception handler for known exceptions in predicted cases
     *
     * @param ex - Exception
     * @return Error with basic description
     */
    @ExceptionHandler(TUIException.class)
    public ResponseEntity<Error> handleException(TUIException ex) {
        Error error = new Error();
        error.code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.message(ex.getTUIExceptionType().name() + ": " + ex.getDescription());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, RestClientException.class, ResponseStatusException.class, NoHandlerFoundException.class})
    public ResponseEntity<Error> handleEx(Exception e) {
        Error error = new Error();
        error.code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    /**
     * This is workaround for 406 status. Spring cuts response body otherwise.
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return String.format("{“status”: %s “Message”: \"%s\" }", "406", exception.getMessage());
    }


}
