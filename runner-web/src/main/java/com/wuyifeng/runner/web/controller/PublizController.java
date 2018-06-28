package com.wuyifeng.runner.web.controller;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.service.CustomerService;
import com.wuyifeng.runner.web.form.LoginForm;
import com.wuyifeng.runner.web.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 公开的控制器，所有不需要身份认证的方法都会放到这个控制器中
 */
@Controller
@RequestMapping("/publiz")
public class PublizController {

    @Autowired
    public CustomerService customerService;


    //进入注册页面
    @GetMapping("/register")
    public String register() {
        return "publiz/register";
    }

    //执行注册操作
    @PostMapping("/register")
    public String register(@Validated RegisterForm registerForm,
                           BindingResult bindingResult,
                           Model model) {

        //验证提交的时候，有两种方式，一种前段验证，通过js验证；第二种通过后端验证
        //后端验证
//        if (username == null || "".equals(username)){
//            model.addAttribute("errorMsg", "用户账号不能为空");
//            return "publiz/register";
//        }
//        if (password == null || "".equals(password)){
//            model.addAttribute("errorMsg", "用户密码不能为空");
//            return "publiz/register";
//        }
//        if (mobile == null || "".equals(mobile)){
//            model.addAttribute("errorMsg", "手机号码不能为空");
//            return "publiz/register";
//        }
//        if (nickname == null || "".equals(nickname)){
//            model.addAttribute("errorMsg", "用户昵称不能为空");
//            return "publiz/register";
//        }
        //

        if (bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();

            for (ObjectError error : bindingResult.getAllErrors()) {
                sb.append(error.getDefaultMessage());
            }
            model.addAttribute("errorMsg", sb.toString());
            return "publiz/register";
        }

        Customer customer = new Customer(
                registerForm.getUsername(),
                registerForm.getNickname(),
                registerForm.getPassword(),
                registerForm.getMobile());

        Customer result = customerService.register(customer);
        if (result != null){
            //注册成功,跳转到登录界面，并携带相关提示信息

            return "publiz/regsuccess";
        }else{
            //注册失败
            return null;
        }

    }

    //进入登录界面
    @GetMapping("/login")
    public String login(){
        return "publiz/login";
    }

    //执行登录操作
    @PostMapping("/login")
    public String login(@Validated LoginForm loginForm,BindingResult bindingResult,
                        Model model, HttpSession session){
        if (bindingResult.hasErrors()){
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            model.addAttribute("errorMsg", errorMsg);
            return "publiz/login";
        }
        Customer customer = customerService.login(loginForm.getUsername(), loginForm.getPassword());
        if (customer == null){
            //登录失败，重新跳转到登录界面，并给出相关提示
            model.addAttribute("errorMsg", "用户名或密码不正确，请重新输入");
            return "publiz/login";
        }else{
            //登录成功,有1个步骤要执行

            //将用户信息存放到session
            session.setAttribute("customer", customer);

            //重定向到订单的首页
            return "redirect:/order/index";
        }
    }

}
