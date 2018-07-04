package com.wuyifeng.runner.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录入口
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String home(){
        return "redirect:/publiz/login";
    }
}
