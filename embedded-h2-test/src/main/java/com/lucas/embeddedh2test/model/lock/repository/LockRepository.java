package com.lucas.embeddedh2test.model.lock.repository;

import com.lucas.embeddedh2test.model.lock.LockEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface LockRepository extends JpaRepository<LockEntity, Long> {

    // Pessimistic Lock 을 위한 메소드
    @Lock(LockModeType.PESSIMISTIC_READ) // READ 는 읽기는 가능, WRITE 는 읽기 조차 불가하게 Lock
    Optional<LockEntity> findWithPessimisticLockByName(String name);


    // Optimistic Lock 을 위한 메소드
    // JPQL, EntityManager.find() 는 명시적으로 LockModeType 을 설정해야함. ->
//     @Lock(LockModeType.OPTIMISTIC) // Optimistic Lock 은 JPA Level 에서 구현하기 때문에 필요 없음
    Optional<LockEntity> findWithOptimisticLockByName(String name);


    LockEntity findByName(String name);
}
