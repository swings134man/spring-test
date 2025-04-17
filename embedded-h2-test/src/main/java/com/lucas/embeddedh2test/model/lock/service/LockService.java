package com.lucas.embeddedh2test.model.lock.service;


import com.lucas.embeddedh2test.model.lock.LockEntity;
import com.lucas.embeddedh2test.model.lock.repository.LockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
