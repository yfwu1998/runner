package com.wuyifeng.runner.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    //进入下单页面
    @GetMapping("/index")
    public String index(){
        return "order/index";
    }
}
