package com.lucas.embeddedh2test.exceptions;

/**
 * @package : com.lucas.embeddedh2test.exceptions
 * @name : ResourceNotFoundException.java
 * @date : 2025. 1. 10. 오후 5:18
 * @author : lucaskang(swings134man)
 * @Description: ResourceNotFoundException Exception - 데이터베이스에서 ID를 찾을 수 없을 때 발생하는 예외
**/
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String datasource, long id) {
        super(datasource + "에서 Param " + id + "를 찾을 수 없습니다.");
    }

    public ResourceNotFoundException(String datasource, String id) {
        super(datasource + "에서 Param " + id + "를 찾을 수 없습니다.");
    }
}
