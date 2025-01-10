package com.lucas.embeddedh2test.config;

import com.lucas.embeddedh2test.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @package : com.lucas.embeddedh2test.config
 * @name : ExceptionControllerAdvice.java
 * @date : 2025. 1. 10. 오후 5:23
 * @author : lucaskang(swings134man)
 * @Description:
**/
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFoundException(ResourceNotFoundException exception) {
        return exception.getMessage();
    }
}
