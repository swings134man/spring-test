package com.lucas.embeddedh2test.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {
    private Long id;
    private String nickname;
    private String email;
    private String address;
}
