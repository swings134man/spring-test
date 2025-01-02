package com.lucas.embeddedh2test.repository;

import com.lucas.embeddedh2test.model.UsersEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @package : com.lucas.embeddedh2test.repository
 * @name : UserRepositoryTest.java
 * @date : 2025. 1. 3. 오전 1:07
 * @author : lucaskang(swings134man)
 * @Description: User Repository Test ()
**/
@DataJpaTest
@TestPropertySource(locations = "classpath:test-application.yml")
public class UserRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void DB_연결확인() {
        // given
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setNickname("test");
        usersEntity.setEmail("test@test.com");
        usersEntity.setAddress("test address");

        // when
        UsersEntity result = usersRepository.save(usersEntity);

        // then
        assertThat(result.getId()).isNotNull();
    }
}
