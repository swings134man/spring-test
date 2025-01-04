## embedded-h2-test
- 해당 모듈은 h2 데이터베이스를 내장하여 사용하는 테스트 모듈입니다.<br/>
h2 DB 는 3가지 타입으로 사용이 가능합니다.

> ### 종류
> 1. In-Memory: 메모리에 데이터를 저장하고, 애플리케이션을 종료하면 데이터가 사라집니다.
>    1. DB에 데이터를 저장하지 않아도 될때 유용합니다.
>    2. 테스트 용도로 많이 사용됩니다.
>    3. 따라서 애플리케이션을 종료하면 데이터베이스도 종료됩니다.<br/><br/>
> 2. Embedded: 파일로 데이터를 저장하고, 애플리케이션을 종료해도 데이터가 유지됩니다.
>    1. 다만 별도의 DB 서버가 운영되지 않고, 애플리케이션 내부에서 데이터베이스를 사용합니다.
>    2. 따라서 애플리케이션을 종료하면 데이터베이스도 종료됩니다.<br/><br/>
> 3. Server: 서버로 데이터를 저장하고, 애플리케이션을 종료해도 데이터가 유지됩니다.
>    1. 별도의 h2 DB 서버를 구동하여 사용합니다.

```yml
# in-memory
url: jdbc:h2:mem:test
driverClassName: org.h2.Driver
username: sa
password:

# embedded
url: jdbc:h2:~/test # ~/test.db 파일로 저장됩니다.
driverClassName: org.h2.Driver
username: sa
password:

# server
url: jdbc:h2:tcp://localhost/~/test # ~/test.db 파일로 저장됩니다.
driverClassName: org.h2.Driver
username: sa
password:
``` 
```java
/**
 * Test resources 가 따로 존재하는경우
 * /test/resources 아래에 프로퍼티 파일 생성 후 사용합니다.
 * test Class 에서 @TestPropertySource(locations = "classpath:test-application.yml") 로 특정 프로퍼티 파일을 사용할 수 있습니다.
 * 
 * Spring 을 사용하는 경우 @ExtendWith(SpringExtension.class) 를 사용하여 테스트를 진행합니다. 이 어노테이션은 JUnit5 에서 제공합니다.
 * 다만 JPA 를 테스트하는 경우라면 @DataJpaTest 를 사용하여 테스트를 진행합니다.
 * 해당 어노테이션 내부에 @ExtendWith(SpringExtension.class) 가 포함되어 있습니다.
 * 
 * @Sql 어노테이션을 사용하여 테스트 전에 SQL 파일을 실행할 수 있습니다.
 */
@DataJpaTest
@TestPropertySource(locations = "classpath:test-application.yml")
@Sql("/sql/data.sql")
class Test{
}
```

---
## Tips! 
- Test Class 전체 테스트시, 어떤 Methods 들이 실행되었는지 Methods 가 안보인다면!!
  - intellij settings -> build, execution, deployment -> build tools -> gradle -> run tests using -> IntelliJ IDEA 설정 변경! 