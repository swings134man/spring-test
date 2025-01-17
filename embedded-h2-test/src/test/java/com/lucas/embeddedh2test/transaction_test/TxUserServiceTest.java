package com.lucas.embeddedh2test.transaction_test;

import com.lucas.embeddedh2test.model.UsersEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @package : com.lucas.embeddedh2test.transaction_test
 * @name : TxUserServiceTest.java
 * @date : 2025. 1. 17. 오후 7:37
 * @author : lucaskang(swings134man)
 * @Description:
**/
@SpringBootTest
@DisplayName("Transaction Service Test")
class TxUserServiceTest {

    @Autowired
    private TxUserService txUserService;

    @AfterEach
    void tearDown() {
        txUserService.deleteAll();
    }

    private UsersEntity createUser() {
        return UsersEntity.builder()
                .nickname("testTx")
                .email("testTx@test.com")
                .address("testTx")
                .build();
    }

    private UsersEntity findUser(String nickName) {
        return txUserService.findByNickName(nickName);
    }

    // update() 에서 Exception 발생시 save() 또한 Rollback 된다.
    @Test
    @DisplayName("Unchecked - Rollback")
    @Order(1)
    void unchecked_rollback() {
        // given
        UsersEntity user = createUser();

        // when
        assertThatThrownBy(() -> txUserService.save(user, true))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Updated Rollback");

        // then
        UsersEntity findUser = findUser(user.getNickname());
        assertThat(findUser).isNull();
    }

    // save() 는 Rollback 되지 않는다.
    @Test
    @DisplayName("Unchecked - Only update Rollback")
    @Order(2)
    void unchecked_not_rollback_save() {
        // given
        UsersEntity user = createUser();

        // when
        txUserService.save_try(user, true);

        // then
        UsersEntity findUser = findUser(user.getNickname());
        assertThat(findUser).isNotNull();
    }

    // ---------- checked Exception ----------


}
