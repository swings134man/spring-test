package com.lucas.embeddedh2test.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @package : com.lucas.embeddedh2test.service
 * @name : TxProxyCallTest.java
 * @date : 2025. 2. 10. 오후 6:29
 * @author : lucaskang(swings134man)
 * @Description: Transaction Proxy Call Test
 * - @Transactional 의 경우 Proxy 객체를 생성하여 호출하는 경우에만 동작한다.
 * - internal() 를 호출하면 Proxy 객체를 Call 했기 때문에 Transaction 이 적용된다.
 * - external() 을 호출하면 내부 Class 의 internal() 을 호출하게 되는데, 이때는 Proxy 객체를 Call 한게 아니기 때문에 Transaction 이 적용되지 않는다.
**/
@Slf4j
@SpringBootTest
class TxProxyCallTest {

    @Autowired
    private TxService txService;

    @Test
    public void printClass() {
        log.info("#### CallService = {}", txService.getClass());
    }

    @Test
    @DisplayName("내부 호출 테스트")
    void internalCall() {
        txService.internal();
    }

    @Test
    @DisplayName("외부 호출 테스트")
    void externalCall() {
        txService.external();
    }
// ---------------------------- Test Configuration Classes ----------------------------
    @TestConfiguration
    static class CallTestConfig {
        @Bean
        public TxService txService() {
            return new TxService();
        }
    }

    @Slf4j
    static class TxService {
        public void external() {
            log.info("## External Call");
            internal();
            printTx();
        }

        @Transactional
        public void internal() {
            log.info("## Internal Call");
            printTx();
        }

        private void printTx() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Transaction Active = {}", isActive);
        }
    }// txService
}// Test Class
