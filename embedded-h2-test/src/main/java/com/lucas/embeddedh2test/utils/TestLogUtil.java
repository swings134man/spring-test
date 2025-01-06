package com.lucas.embeddedh2test.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @package : com.lucas.embeddedh2test.utils
 * @name : TestUtil.java
 * @date : 2025. 1. 6. 오후 6:15
 * @author : lucaskang(swings134man)
 * @Description: Test Util Class: 해당 클래스는 Business Logic 에서 해당 객체를 사용하는 테스트 목적의 클래스입니다.
 * Test 시 해당 Class 를 Mocking 하여 테스트를 진행합니다.
 * @Confuguration: 일 경우 @Bean 메서드는 void 를 반환할 수 없기에 @Component 로 선언.
**/
@Component
@Slf4j
public class TestLogUtil {

    public void print() {
      log.info("###### Test Util Class print!!");
    }

}
