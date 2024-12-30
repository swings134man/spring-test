package com.lucas.sample.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("Invalid Input Size, you must input two numbers and an operator");
    }
}
