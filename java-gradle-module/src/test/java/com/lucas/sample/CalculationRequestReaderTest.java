package com.lucas.sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculationRequestReaderTest {

    @Test
    @DisplayName("CalculationRequestReader read() Test")
    void read() {
        // given
        CalculationRequestReader reader = new CalculationRequestReader();

        // when
        System.setIn(new ByteArrayInputStream("2 + 3".getBytes())); // 테스트를 위해 System.in을 임의의 값으로 설정
        CalculationRequest readResult = reader.read();

        // then
        assertEquals(2, readResult.getNum1());
        assertEquals("+", readResult.getOperator());
        assertEquals(3, readResult.getNum2());
    }

}