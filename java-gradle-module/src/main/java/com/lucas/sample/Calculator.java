package com.lucas.sample;

import com.lucas.sample.exceptions.InvalidOperatorException;

/**
 * @package : com.lucas.sample
 * @name : Calculator.java
 * @date : 2024. 12. 30. 오후 5:00
 * @author : lucaskang(swings134man)
 * @Description: Calculator Function Sample Application
**/
public class Calculator {
    public long calculate(long num1, String operator, long num2) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new InvalidOperatorException();
        };
    }
}
