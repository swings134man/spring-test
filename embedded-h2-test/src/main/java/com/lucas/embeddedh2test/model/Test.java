package com.lucas.embeddedh2test.model;

import com.lucas.embeddedh2test.config.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "test")
@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class Test extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;
    private String message;
}
