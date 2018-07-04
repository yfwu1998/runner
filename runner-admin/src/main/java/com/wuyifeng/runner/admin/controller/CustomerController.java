package com.wuyifeng.runner.admin.controller;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.domain.Order;
import com.wuyifeng.runner.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", value = "start") Integer start,
                        @RequestParam(defaultValue = "10", value = "limit") Integer limit,
                        HttpSession session, Model model) {
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
        Pageable pageable = new PageRequest(start, limit, sort);
        Page<Customer> page = customerService.list(pageable);
        model.addAttribute("page", page);
        return "customer/index";
    }

    /**
     * 订单详情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam Long id, Model model) {
        //调用服务层获取订单信息
        Customer customer = customerService.get(id);

        //将订单信息数据添加的model对象中
        model.addAttribute("customer", customer);

        //跳转到订单详情页面
        return "customer/detail";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam Long id,
                          @RequestParam Integer status, Model model) throws Exception {
        Customer customer = customerService.confirmToDistributor(id, status);
        //将订单信息数据添加的model对象中
        model.addAttribute("customer", customer);
        model.addAttribute("tip", status == 2 ? "确认通过" : "确认不通过");
        //跳转到订单详情页面
        return "customer/detail";

    }
}
