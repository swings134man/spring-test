package com.lucas.sample;

import com.lucas.sample.exceptions.BadRequestException;
import com.lucas.sample.exceptions.InvalidOperatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationRequestTest {

    @Test
    void 유효한_모든_경우 () {
        new CalculationRequest(new String[]{"1", "+", "2"});
        new CalculationRequest(new String[]{"1", "-", "2"});
        new CalculationRequest(new String[]{"1", "*", "2"});
        new CalculationRequest(new String[]{"1", "/", "2"});
    }


    @Test
    void 유효한_숫자_파싱(){
        // given
        String[] params = {"1", "+", "2"};

        // when
        CalculationRequest request = new CalculationRequest(params);

        // then
        assertEquals(1, request.getNum1());
        assertEquals(2, request.getNum2());
        assertEquals("+", request.getOperator());
    }

    @Test
    void 유효한_숫자_파싱_3자리(){
        // given
        String[] params = {"100", "+", "222"};

        // when
        CalculationRequest request = new CalculationRequest(params);

        // then
        assertEquals(100, request.getNum1());
        assertEquals(222, request.getNum2());
        assertEquals("+", request.getOperator());
    }

    @Test
    void 유효하지_않은_인풋_숫자(){
        // given
        String[] params = {"100", "+"};

        // when
        assertThrows(BadRequestException.class, () -> new CalculationRequest(params));
    }

    @Test
    void 유효하지_않은_연산자(){
        // given
        String[] params = {"100", "&", "222"};

        // when
        assertThrows(InvalidOperatorException.class, () -> new CalculationRequest(params));
    }

    @Test
    void 유효하지_않은_연산자_길이(){
        // given
        String[] params = {"100", "+-", "222"};

        // when
        assertThrows(InvalidOperatorException.class, () -> new CalculationRequest(params));
    }
}//class