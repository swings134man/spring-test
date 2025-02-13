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
 * @name : TxCallTest.java
 * @date : 2025. 2. 10. 오후 9:16
 * @author : lucaskang(swings134man)
 * @Description: Transaction Call Test
 * - TxProxyCallTest.class 와 비교했을때는 비슷하지만 실제로 TX 가 적용되는 기준은 다름.
 *   -> 이 클래스에서는 호출되는 Method 가 @Transactional 이 적용되어 있기 때문에 TX 가 적용된다.
 *   --> TxProxyCallTest.class 에서는 호출하는 Method 에 @Transactional 이 적용되어 있지 않기 때문에 TX 가 적용되지 않는다.
 *
 *   - Test Methods 3 개 각각 설명
 *      1. callTest() : create(), update() 무엇을 호출하던 save() 트랜잭션으로 묶인다(@Transactional 존재여부 상관 X)
 *      2. updateCall() : update() 는 @Transactional 이 존재 하지 않기에 Transaction 이 적용되지 않는다.
 *      3. createCall() : create() 는 @Transactional 이 존재하기에 Transaction 이 적용된다. 다만 save 가 아닌 create 트랜잭션으로 묶인다.
 *      4. requireNewCall() : TxProxyCallTest 와 마찬가지로 requireNewCall 은 새로운 Transaction 을 생성할것이라 예측됬지만, 같은 Proxy 객체 호출이기에
 *                    save() 트랜잭션으로 묶인다.
**/
@SpringBootTest
@Slf4j
public class TxCallTest {

    @Autowired
    private TxService txService;

    @Test
    @DisplayName("TX 호출 테스트")
    void callTest() {
        log.info("### Call Test");
        txService.save("create"); // create() 는 save 트랜잭션에 묶여있다.
    }

    @Test
    @DisplayName("Tx update 호출 테스트 - @Transactional 없음")
    void updateCall() {
        log.info("### update Call Test");
        txService.update(); // null
    }

    @Test
    @DisplayName("Tx save 호출 테스트 - @Transactional 존쟈")
    void createCall() {
        log.info("### create Call Test");
        txService.create(); // create Transactional
    }

    @Test
    @DisplayName("Require new Tx 호출 테스트")
    void requireNewCall() {
        log.info("### Require New Call Test");
        txService.save("new");
    }

    // --------- Test Configuration Classes ------------
    @TestConfiguration
    static class TxCallClass{
        @Bean
        public TxService txService() {
            return new TxService();
        }
    }

    static class TxService {

        @Transactional
        public void save(String isSave) {
            log.info("### Save Call");
            printTx();

            if(isSave.equals("create")) {
                create();
            }else if(isSave.equals("update")){
                update();
            }else if(isSave.equals("new")) {
                newTx();
            }
        }

        @Transactional
        protected void create() {
            log.info("#### Create Call");
            printTx();
        }

        protected void update() {
            log.info("#### Update Call");
            printTx();
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        protected void newTx() {
            log.info("#### Require New Tx Call");
            printTx();
        }

        private void printTx() {
            log.info("Tx ID : {}", TransactionSynchronizationManager.getCurrentTransactionName());
        }

    }

}//class
