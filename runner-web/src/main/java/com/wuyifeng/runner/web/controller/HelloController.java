package com.wuyifeng.runner.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    //http://localhost:8089/hello/say.html
    @GetMapping(value = "/say")
    public String say(Model model){
        model.addAttribute("greet", "Hello Thymeleaf!");
        return "hello/say";
    }
}

