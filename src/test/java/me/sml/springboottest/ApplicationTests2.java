package me.sml.springboottest;

import me.sml.springboottest.sample.HelloService;
import me.sml.springboottest.sample.SampleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class ApplicationTests2 {


    /*
    * @WebMvcTest는 Web과 관련된 애들만 빈으로 등록되기 떄문에
    * Service와 같이 필요한것은 @MockBean을 사용하여 등록시켜주어야 함.
    * */
    @MockBean
    HelloService helloService;


    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception{
        when(helloService.getName()).thenReturn("posibean");

        mockMvc.perform(get("/hello"))
                .andExpect(content().string("hello posibean"));
    }
}
