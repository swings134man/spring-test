package com.lucas.embeddedh2test.service;

import com.lucas.embeddedh2test.model.UsersEntity;
import com.lucas.embeddedh2test.utils.TestLogUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @package : com.lucas.embeddedh2test.service
 * @name : UsersServiceTest.java
 * @date : 2025. 1. 6. 오후 5:35
 * @author : lucaskang(swings134man)
 * @Description: UsersServiceTest
 *
 * @SpringBootTest : 스프링 부트 테스트 어노테이션(의존성 주입)
**/
@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:test-application.yml"})
@SqlGroup({
        @Sql(value = "/sql/user-service-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@DisplayName("UserService 테스트")
public class UserServiceTest {

    @Autowired
    private UserService usersService;

    @MockBean // Mocking 객체:
    private TestLogUtil testLogUtil;

    @Test
    @DisplayName("Email 로 사용자 조회")
    void findByEmail() {
        // given
        String email = "test@test.com";

        // when
        UsersEntity result = usersService.findByEmail(email);

        // then
        assertThat(result.getNickname()).isEqualTo("test");
        System.out.println("findByEmail : " + result);
    }

    @Test
    @DisplayName("ID 로 사용자 조회")
    void findById() {
        // given
        Long id = 1L;

        // when
        UsersEntity result = usersService.findById(id);

        // then
        assertThat(result.getNickname()).isEqualTo("test");
        System.out.println("findById : " + result);
    }

    @Test
    @DisplayName("사용자 저장")
    void saveUser() {
        // given
        UsersEntity user = UsersEntity.builder()
                .nickname("test2")
                .email("test2@test.com")
                .address("test2")
                .build();

        // when
        UsersEntity result = usersService.saveUser(user);

        // Dummy 객체: Mocking 객체 (해당 객체가 호출되도 아무것도 하지않는, 이때 bean 은 void 메소드만 가능)
        BDDMockito.doNothing().when(testLogUtil).print();

        // then
        assertThat(result.getId()).isNotNull();
        System.out.println("saveUser : " + result);
    }

    @Test
    @DisplayName("사용자 update")
    void update() {
        // given
        UsersEntity user = UsersEntity.builder()
                .id(1L)
                .nickname("test123")
                .email("test@test.com")
                .address("서울시 강남구")
                .build();

        // when
        UsersEntity result = usersService.update(user);

        // then
        assertThat(result.getNickname()).isEqualTo("test123");
    }
}
