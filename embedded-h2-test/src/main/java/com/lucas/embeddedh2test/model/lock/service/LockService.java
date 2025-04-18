package com.lucas.embeddedh2test.model.lock.service;


import com.lucas.embeddedh2test.model.lock.LockEntity;
import com.lucas.embeddedh2test.model.lock.repository.LockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LockService {

    private final LockRepository lockRepository;


    // 비관적(Pessimistic)
    @Transactional
    public void findPessimistic(String name) {
        LockEntity lockEntity = lockRepository.findWithPessimisticLockByName(name)
                .orElseThrow(IllegalArgumentException::new);
        lockEntity.decrementStock();
    }


    // 낙관적(Optimistic)
    @Transactional
    public void findOptimistic(String name) {
        LockEntity lockEntity = lockRepository.findWithOptimisticLockByName(name)
                .orElseThrow(IllegalArgumentException::new);
        lockEntity.decrementStock();
    }

    // 낙관적: Retry
    @Transactional
    @Retryable(
            value = {ObjectOptimisticLockingFailureException.class}, // 재시도 할 예외 Class(제외는 exclude)
            maxAttempts = 5, // 최대 재시도 횟수
            backoff = @Backoff(delay = 2000) // 재시도 간격(multiplier 의경우 지연시간을 곱함 ex: 1000 * 2 = 2000ms ...)
    )
    public void optimisticRetry(String name) {
        LockEntity lockEntity = lockRepository.findWithOptimisticLockByName(name)
                .orElseThrow(IllegalArgumentException::new);
        lockEntity.decrementStock();
    }

    // 재시도 실패 시 호출되는 메소드 - 같은 Class 내에 @Retryable 에 적용됨
    // 이때 Method Signature 를 기준으로 어떤 @Recover 를 호출할지 결정됨
    @Recover
    public void recover(ObjectOptimisticLockingFailureException e, String name) {
        log.info("Recover Method Called = {}", e.getMessage());
        // 예외 처리 로직
        // 예를 들어, 재시도 실패 시 다른 작업을 수행하거나 알림을 보낼 수 있습니다.
    }
}
