package com.hellocabs.exceptionHandler;

import com.hellocabs.exception.HelloCabsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class HelloCabsAdvisor {

    /**
     * Method used to show the  exception which is handle by MethodArgumentNotValidException
     * with the help of exception handler method
     *
     * @param {@link MethodArgumentNotValidException}exception
     * @return {@link String}return the exception with message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public Map<String,String> exceptionHandler(MethodArgumentNotValidException exception) {
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    /**
     * Method used to show the  exception which is handle by RuntimeException
     * with the help of exception handler method
     *
     * @param {@link RuntimeException}exception
     * @return {@link String}return the exception with message
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = HelloCabsException.class)
    public Map<String, Object> exceptionHandler(RuntimeException runtimeException) {
        Map<String, Object> errors = new LinkedHashMap<>();

        errors.put("timestamp", LocalDateTime.now());
        errors.put("message", runtimeException.getMessage());
        return errors;
    }
}
