package com.lucas.embeddedh2test.repository;

import com.lucas.embeddedh2test.model.UsersEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

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
@Sql("/sql/user-repository-test-data.sql")
public class UserRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DisplayName("ID 로 사용자 조회")
    void findById() {
        //given
        // when
        Optional<UsersEntity> findResult = usersRepository.findById(1L);

        // then
        assertThat(findResult.isPresent()).isTrue();
    }

    @Test
    @DisplayName("조회결과 없으면 Optional.empty() 반환")
    void findById_optional_empty() {
        //given
        // when
        Optional<UsersEntity> findResult = usersRepository.findById(10L);

        // then
        assertThat(findResult.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("email 로 사용자 조회")
    void findByEmail() {
        //given
        // when
        Optional<UsersEntity> findResult = usersRepository.findByEmail("test@test.com");

        // then
        assertThat(findResult.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Email 조회결과 없으면 Optional.empty() 반환")
    void findByEmail_optional_empty() {
        //given
        // when
        Optional<UsersEntity> findResult = usersRepository.findByEmail("test10@test.com");

        // then
        assertThat(findResult.isEmpty()).isTrue();
    }
}
