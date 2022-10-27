package com.hellocabs.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HelloCabsAdvisor {

    /**
     * Method used to show the  exception which is handle by MethodArgumentNotValidException
     * with the help of exception handler method
     *
     * @param {@link MethodArgumentNotValidException}exception
     * @return {@link String}return the exception with message
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public String exceptionHandler(MethodArgumentNotValidException exception) {
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return " INVALID ENTRY :: " + errors;
    }

    /**
     * Method used to show the  exception which is handle by RuntimeException
     * with the help of exception handler method
     *
     * @param {@link RuntimeException}exception
     * @return {@link String}return the exception with message
     */
    @ExceptionHandler(value = RuntimeException.class)
    public String exceptionHandler(RuntimeException exception) {
        return "Exception " + exception.getMessage();
    }
}
