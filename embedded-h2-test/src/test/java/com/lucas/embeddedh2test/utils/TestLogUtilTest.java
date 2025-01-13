package com.lucas.embeddedh2test.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * @package : com.lucas.embeddedh2test.utils
 * @name : TestLogUtilTest.java
 * @date : 2025. 1. 13. 오후 4:43
 * @author : lucaskang(swings134man)
 * @Description: TestLogUtil 클래스 테스트
**/
@SpringBootTest
public class TestLogUtilTest {

    // Mock 객체로 주입
    @Spy
    private TestLogUtil testLogUtil;

    // 타겟 클래스 내부의 어노테이션 필드 초기화
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void printTest() {
        // when
        testLogUtil.print();

        // then
        verify(testLogUtil).print(); // mock 객체 메소드 호출 확인
    }
}
