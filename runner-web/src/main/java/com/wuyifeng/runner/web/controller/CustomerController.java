package com.wuyifeng.runner.web.controller;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model){
        Customer customer = (Customer) session.getAttribute("customer");
        Customer customer1 = customerService.get(customer.getId());
        model.addAttribute("customer", customer1);
        return "customer/profile";
    }

    @GetMapping("/apply")
    public String apply(HttpSession session, Model model){
        Customer customer = (Customer) session.getAttribute("customer");
        Customer result = customerService.applyToDistributor(customer.getId());

        model.addAttribute("customer", result);
        model.addAttribute("tip", "您已提交申请，请耐心等待");
        return "customer/profile";
    }
}
