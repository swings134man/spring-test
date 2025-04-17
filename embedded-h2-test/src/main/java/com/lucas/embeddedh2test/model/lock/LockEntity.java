package com.lucas.embeddedh2test.model.lock;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @package : com.lucas.embeddedh2test.model.lock
 * @name : LockEntity.java
 * @date : 2025. 4. 17. 오후 11:59
 * @author : lucaskang(swings134man)
 * @Description: JPA Level 에서의 Lock Test 를 위한 Entity
 * - Value(@Version) 를 통해 Optimistic Lock 을 구현
 * - Pessimistic Lock 은 Repository Level 에서 구현한다(LockRepository)
 * - @Version 의 경우 아무것도 설정하지 않는다면 default = 0 이다.
 *      -
**/
@Entity(name = "lock")
@Data
public class LockEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long stock;

    @Version // Optimistic Lock 을 위한 Field: default = 0
    private Long version;


    public void decrementStock() {
        this.stock--;
    }
}
