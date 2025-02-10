package com.lucas.embeddedh2test.transaction_test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package : com.lucas.embeddedh2test.transaction_test
 * @name : TxProxyService.java
 * @date : 2025. 2. 10. 오후 5:31
 * @author : lucaskang(swings134man)
 * @Description: @Transactional Proxy Service
 * - @Transactional 은 Proxy 기반으로 동작한다.
 * - Proxy 기반 동작을 확인하기 위한 Service Class
 *   - 즉 같은 Class 의 Method 를 호출하면 Proxy 객체로 호출하는게 아니기 떄문에 @Transactional 이 동작하지 않는다.
**/
@Service
@RequiredArgsConstructor
@Slf4j
public class TxProxyService {

    @Transactional
    public void save(String msg) {

    }

    @Transactional
    public void something() {

    }
}
