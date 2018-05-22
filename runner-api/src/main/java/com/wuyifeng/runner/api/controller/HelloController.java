package com.wuyifeng.runner.api.controller;

import com.wuyifeng.runner.core.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;
    //http://localhost:8088/api/hello/say?name=World
    @GetMapping("/say")
    public String say(@RequestParam String name){
        return helloService.say(name);
    }

}
