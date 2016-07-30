package com.yihukurama.springbootdemo.framework.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sample {
	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }
	
	@RequestMapping("/sayhello")
    String sayHello() {
        return "Hello guys!";
    }
}
