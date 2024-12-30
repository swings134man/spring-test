package com.lucas.sample.exceptions;

/**
 * @package : com.lucas.sample.exceptions
 * @name : InvalidOperatorException.java
 * @date : 2024. 12. 30. 오후 4:55
 * @author : lucaskang(swings134man)
 * @Description: Custom Exception Class - Invalid Operator Exception
**/
public class InvalidOperatorException extends RuntimeException{
    public InvalidOperatorException() {
        super("Invalid Operator, Please check the operator(+, -, *, /)");
    }
}
