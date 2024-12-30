package com.lucas.sample;

import com.lucas.sample.exceptions.InvalidOperatorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @package : com.lucas.sample
 * @name : CalculatorTest.java
 * @date : 2024. 12. 30. 오후 5:07
 * @author : lucaskang(swings134man)
 * @Description: Calculator 의 기능 Test
**/
public class CalculatorTest {

    @Test
    @DisplayName("Calculator Plus Test")
    public void calcPlus() {
        // given
        Calculator calculator = new Calculator();
        long num1 = 3;
        long num2 = 4;
        String operator = "+";

        // when
        long resultValue = calculator.calculate(num1, operator, num2);

        // then
        System.out.println("resultValue = " + resultValue);
        assertEquals(7, resultValue); //Junit assertions
    }

    @Test
    @DisplayName("Calculator Minus Test")
    public void calcMinus() {
        // given
        Calculator calculator = new Calculator();
        long num1 = 3;
        long num2 = 4;
        String operator = "-";

        // when
        long resultValue = calculator.calculate(num1, operator, num2);

        // then
        System.out.println("resultValue = " + resultValue);
        assertEquals(-1, resultValue); //Junit assertions
    }

    @Test
    @DisplayName("Calculator Multiple Test")
    public void calcMultiple() {
        // given
        Calculator calculator = new Calculator();
        long num1 = 3;
        long num2 = 4;
        String operator = "*";

        // when
        long resultValue = calculator.calculate(num1, operator, num2);

        // then
        System.out.println("resultValue = " + resultValue);
        assertEquals(12, resultValue); //Junit assertions
    }

    @Test
    @DisplayName("Calculator Divide Test")
    public void calcDivide() {
        // given
        Calculator calculator = new Calculator();
        long num1 = 12;
        long num2 = 4;
        String operator = "/";

        // when
        long resultValue = calculator.calculate(num1, operator, num2);

        // then
        System.out.println("resultValue = " + resultValue);
        assertEquals(3, resultValue); //Junit assertions
    }

    // Exception Test
    @Test
    @DisplayName("Calculator Exception Test")
    public void calcException() {
        // given
        Calculator calculator = new Calculator();
        long num1 = 12;
        long num2 = 4;
        String operator = "%";

        // when
        // then
        assertThrows(InvalidOperatorException.class, () -> {
            calculator.calculate(num1, operator, num2);
        });
    }
}//class
