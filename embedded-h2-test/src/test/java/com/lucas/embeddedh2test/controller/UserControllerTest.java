package com.lucas.embeddedh2test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.embeddedh2test.model.UsersEntity;
import com.lucas.embeddedh2test.utils.TestLogUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @package : com.lucas.embeddedh2test.controller
 * @name : UserControllerTest.java
 * @date : 2025. 1. 10. 오후 4:15
 * @author : lucaskang(swings134man)
 * @Description: UserController Test Code
 *
 * - @AutoConfigureMockMvc: MockMvc를 자동 설정, HTTP 요청, 응답 테스트하기 위함
 * - @AutoConfigureTestDatabase: 테스트 데이터베이스 자동 설정
**/
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/user-service-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private TestLogUtil testLogUtil;

    @Test
    @DisplayName("유저 정보 전달받기 - By ID")
    void findUser() throws Exception {
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.nickname").value("test"))
                .andExpect(jsonPath("$.address").value("test address"));
    }

    @Test
    @DisplayName("존재하지않는 유저 정보 전달받기 - By ID")
    void notFoundUser() throws Exception {
        mockMvc.perform(get("/api/v1/users/1000000"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Users에서 Param 1000000를 찾을 수 없습니다.")); // 메시지 검증
    }

    @Test
    @DisplayName("유저 정보 찾기 - By Email")
    void findByEmail() throws Exception{
        mockMvc.perform(get("/api/v1/users/email/test@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.nickname").value("test"))
                .andExpect(jsonPath("$.address").value("test address"));
    }

    @Test
    @DisplayName("유저 정보 저장하기")
    void save() throws Exception {
        UsersEntity entity = UsersEntity.builder()
                .id(null)
                .email("test3@test.com")
                .nickname("test3")
                .address("test address3")
                .build();

        BDDMockito.doNothing().when(testLogUtil).print();

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.email").value("test3@test.com"))
                .andExpect(jsonPath("$.nickname").value("test3"))
                .andExpect(jsonPath("$.address").value("test address3"));
    }

    @Test
    @DisplayName("유저 정보 수정하기 - By ID")
    void update() throws Exception {
        UsersEntity entity = UsersEntity.builder()
                .id(1L)
                .email("test2@test.com")
                .nickname("test2")
                .address("test address2")
                .build();

        mockMvc.perform(put("/api/v1/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity))
                )
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test2@test.com"))
                .andExpect(jsonPath("$.nickname").value("test2"))
                .andExpect(jsonPath("$.address").value("test address2"));
    }

    @Test
    @DisplayName("유저 정보 삭제하기 - By ID")
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Users에서 Param 1를 찾을 수 없습니다.")); // 메시지 검증
    }
}//class
