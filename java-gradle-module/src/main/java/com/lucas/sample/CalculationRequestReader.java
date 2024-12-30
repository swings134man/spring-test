package com.lucas.sample;

import java.util.Scanner;

/**
 * @package : com.lucas.sample
 * @name : CalculationRequestReader.java
 * @date : 2024. 12. 30. 오후 5:13
 * @author : lucaskang(swings134man)
 * @Description: 사용자의 요청을 입력받는 Reader Class
**/
public class CalculationRequestReader {

    public CalculationRequest read() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the two numbers and with the operator(e.g 1 + 2): ");
        String result = sc.nextLine();
        String[] parts = result.split(" ");
        return new CalculationRequest(parts);
    }
}
