package me.sml.springboottest;

import me.sml.springboottest.sample.HelloService;
import me.sml.springboottest.sample.SampleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
// MockMvc 주입
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    /*
    * 아무것도 안해도 주입받을 수 있다.
    * */
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    HelloService mockHelloService;

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello sangmin"))
                .andDo(print());
    }

    @Test
    public void hello2() throws Exception {
        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello sangmin");
    }

    @Test
    public void hello3() throws Exception{
        when(mockHelloService.getName()).thenReturn("posibean");
        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello posibean");
    }


    @Test
    public void hello4() throws Exception{
        when(mockHelloService.getName()).thenReturn("posibean");

        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("hello posibean");
    }

}


