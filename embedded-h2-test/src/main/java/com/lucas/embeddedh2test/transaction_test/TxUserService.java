package com.lucas.embeddedh2test.transaction_test;

import com.lucas.embeddedh2test.model.UsersEntity;
import com.lucas.embeddedh2test.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package : com.lucas.embeddedh2test.transaction_test
 * @name : TxUserService.java
 * @date : 2025. 1. 17. 오후 7:27
 * @author : lucaskang(swings134man)
 * @Description: Transaction propagation 테스를 위한 Service Class
 *
 * - 상황 4개
 * 1. Unchecked - all Rollback
 * 2. Unchecked - not Rollback
 * 3. Checked -
 * 4. Checked -
**/
@Service
@RequiredArgsConstructor
@Slf4j
public class TxUserService {

    private final UsersRepository repository;

    // --------- Transactional Propagation: Unchecked Exception (basic Rollback) ------------
    // update() 에서 Exception 발생시 save() 또한 Rollback 된다.
    @Transactional
    public void save(UsersEntity user, boolean isException) {
        UsersEntity saveEntity = repository.save(user);
        log.info("### UnChecked Save Success !!!");

        try {
            update(saveEntity, isException);
        } catch (Exception e) {
            log.info("All RollBack");
            throw e;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(UsersEntity user, boolean isException) {
        user.setNickname("updated");
        UsersEntity updatedEntity = repository.save(user);
        if(isException) {
            throw new RuntimeException("Updated Rollback");
        }
    }

    // --------- Transactional Propagation: Unchecked Exception (save() Not Rollback) ------------
    @Transactional
    public void save_try(UsersEntity user, boolean isException) {
        UsersEntity saveEntity = repository.save(user);
        log.info("### UnChecked Save Success !!!");

        try {
            update_try(saveEntity, isException);
        } catch (Exception e) {
            log.info("Updated Rollback Save Not RollBack");
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update_try(UsersEntity user, boolean isException) {
        user.setNickname("updated");
        UsersEntity updatedEntity = repository.save(user);
        if(isException) {
            throw new RuntimeException("Updated Rollback");
        }
    }

    // --------- Transactional Propagation: Checked Exception (basic Not Rollback) ------------




    // --------- function Methods  ------------
    @Transactional(readOnly = true)
    public UsersEntity findByNickName(String nickName) {
        return repository.findByNickname(nickName);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
}