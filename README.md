# 테스트

### WebEnvironment.MOCK
webEnvironment를 MOCK으로 하면 내장 톰캣이 구동되지 않으며, MOCK UP을 통하여 테스트를 진행할 수 있다.
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
```
MockMvc를 가장 쉽게 주입받을 수 있는 방법
```java
@AutoConfigureMockMvc

@Autowired
MockMvc mockMvc;
```

### WebEnvironment.RANDOM_PORT 
내장 톰캣이 구동됨. 따라서 MockMvc 대신 RestTemplate, TestRestTemplate, WebTestClient등을 사용하여 Test를 진행하여야 함.

```java
@Autowired
TestRestTemplate testRestTemplate;
```
 
### MockBean
Service만 Mocking하여 사용할 수 있음. 

```java
@MockBean
Hello Service mockHelloService;

//import static org.mockito.Mockito.when;
when(mockHelloService.getName()).thenReturn("another string");
```

### TestRestTemplate VS WebTestClient

TestRestTemplate은 동기 방식, WebTestClient는 비동기 방식이며
WebTestClient를 사용하기 위해서는 Web Flux를 주입받아야한다.

Maven
```markdown
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

Gradle
```markdown
compile group: 'org.springframework.boot', name: 'spring-boot-starter-webflux'
```


### @WebMvcTest

Slicing Test의 한가지로, Controller 하나에 대한 테스트만이 진행된다.

```java
@WebMvcTest(SampleController.class)
```

또한, WebMvcTest는 Slice Test라 관련된 것들만 빈으로 등록하기 때문에
필요한 빈들은 직접 주입해야한다.

그리고 MockMvc를 활용해서 Test를 진행해야한다.


### 슬라이스 테스트

* @JsonTest
* @WebMvcTest
* @WebFluxTest
* @DataJpaTest

SpringBootTest는 통합테스트다. Spring Boot Application을 기반으로 모든 빈을 등록해서 테스트를 수행한다.
