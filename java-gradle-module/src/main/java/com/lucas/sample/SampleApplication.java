package com.lucas.sample;

/**
 * @package : com.lucas.sample
 * @name : SampleApplication.java
 * @date : 2024. 12. 30. 오후 4:52
 * @author : lucaskang(swings134man)
 * @Description: Calculator Function Sample Application
**/
public class SampleApplication {
    public static void main(String[] args) {
        CalculationRequest reader = new CalculationRequestReader().read();
        long result = new Calculator().calculate(reader.getNum1(), reader.getOperator(), reader.getNum2());

        System.out.println("Result: " + result);
    }//main
}
