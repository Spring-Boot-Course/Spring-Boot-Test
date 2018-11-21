package me.sml.springboottest.sample;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return "hello " + helloService.getName();
    }
}
