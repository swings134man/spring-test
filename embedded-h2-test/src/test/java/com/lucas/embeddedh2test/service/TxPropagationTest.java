package com.lucas.embeddedh2test.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @package : com.lucas.embeddedh2test.service
 * @name : TxPropagationTest.java
 * @date : 2025. 2. 13. 오후 2:47
 * @author : lucaskang(swings134man)
 * @Description: Transaction Propagation Test
 *
 * - 같은 Proxy 객체 내부에서 Call 을 하게 되면, Propagation 을 적용해도 같은 Transaction 에 묶인다.
 * - 그에 따라 새로운 Transaction 을 생성하려면 Proxy 객체를 호출하거나, 다른 객체를 호출 해야만 한다.
 *
 * - 아래의 테스트 Class 는 정상적으로 새로운 Transaction 을 생성 하는 예제.
**/
@SpringBootTest
@Slf4j
class TxPropagationTest {

    @Autowired
    private NewTxService newTxService;

    @Test
    @DisplayName("Transaction 전파 테스트")
    @Transactional
    void txPropagationTest() {
        log.info("## Tx Methods Call");
        printTx();

        newTxService.newSave();
    }


    // --------- Test Configuration Classes ------------
    @TestConfiguration
    static class TxCallClass{
        @Bean
        public NewTxService newTxService() {
            return new NewTxService();
        }
    }
    // --------- Test Target Classes ------------
    static class NewTxService {
        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public void newSave() {
            log.info("### New Save");
            printTx();
        }

    }

    // --------- Common Method ------------
    public static void printTx() {
            log.info("Tx ID : {}", TransactionSynchronizationManager.getCurrentTransactionName());
    }
}// class
