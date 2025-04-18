package com.lucas.embeddedh2test.model.lock.service;

import com.lucas.embeddedh2test.model.lock.LockEntity;
import com.lucas.embeddedh2test.model.lock.repository.LockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:test-application.yml"})
@DisplayName("JPA Lock Test")
class LockServiceTest {

    @Autowired
    private LockService lockService;

    @Autowired
    private LockRepository lockRepository;

    @BeforeEach
    void setUp() {
        lockRepository.deleteAll();

        LockEntity lockEntity = new LockEntity();
        lockEntity.setName("test");
        lockEntity.setStock(10L);
        lockRepository.save(lockEntity);
    }


    // Thread 모두 Query 발생: Stock 0개가 될수 없음.
    @Test
    @DisplayName("낙관적 락 테스트")
    void optimistic_lock() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    lockService.findOptimistic("test");
                } catch (Exception e) {
                    System.out.println("낙관적 락 실패: " + e.getMessage() + " - " + e.getClass().getSimpleName());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        LockEntity lockEntity = lockRepository.findByName("test");
        System.out.println("남은 재고: " + lockEntity.getStock());
        assertNotEquals(0L, lockEntity.getStock());
    }

    // Thread 모두 Query 발생: Stock 0개 됨.
    @Test
    @DisplayName("비관적 락 테스트")
    void pessimistic_lock() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    lockService.findPessimistic("test");
                } catch (Exception e) {
                    System.out.println("비관적 락 실패: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        LockEntity lockEntity = lockRepository.findByName("test");
        System.out.println("남은 재고: " + lockEntity.getStock());
        assertEquals(0L, lockEntity.getStock());
    }

    // Retry 를 수행하더라도 모두가 성공할것이란 보장은 없다.
    // Catch 에 대한부분은 @Recover 에서 처리됨.
    @Test
    @DisplayName("낙관적 락 Retry 테스트")
    void optimistic_lock_retry() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    lockService.optimisticRetry("test");
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        LockEntity lockEntity = lockRepository.findByName("test");
        System.out.println("남은 재고: " + lockEntity.getStock());
        assertNotEquals(0L, lockEntity.getStock());
    }
}