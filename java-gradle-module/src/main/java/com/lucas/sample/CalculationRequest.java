package com.lucas.sample;

import com.lucas.sample.exceptions.BadRequestException;
import com.lucas.sample.exceptions.InvalidOperatorException;

/**
 * @package : com.lucas.sample
 * @name : CalculationRequest.java
 * @date : 2024. 12. 30. 오후 5:18
 * @author : lucaskang(swings134man)
 * @Description: Calculator 의 필요 데이터 객체
 * - num1 : 첫번째 숫자
 * - num2 : 두번째 숫자
 * - operator : 연산자
 * - 올바르지 않은 입력, 연산자에 대한 검증로직 존재.
**/
public class CalculationRequest {

    private final long num1;
    private final long num2;
    private final String operator;

    public CalculationRequest(String[] params) {
        if(params.length != 3) throw new BadRequestException();
        if(params[1].length() != 1 || isOperator(params[1])) throw new InvalidOperatorException();

        this.num1 = Long.parseLong(params[0]);
        this.num2 = Long.parseLong(params[2]);
        this.operator = params[1];
    }

    private boolean isOperator(String operator) {
        return !operator.equals("+") &&
            !operator.equals("-") &&
            !operator.equals("*") &&
            !operator.equals("/");
    }

    public long getNum1() {
        return num1;
    }

    public long getNum2() {
        return num2;
    }

    public String getOperator() {
        return operator;
    }
}
