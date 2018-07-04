package com.wuyifeng.runner.admin.controller;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.domain.Manager;
import com.wuyifeng.runner.core.domain.Order;
import com.wuyifeng.runner.core.service.CustomerService;
import com.wuyifeng.runner.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    /**
     * 订单列表
     * @param start
     * @param limit
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0", value = "start") Integer start,
                        @RequestParam(defaultValue = "10", value = "limit") Integer limit,
                        HttpSession session, Model model) {
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
        Pageable pageable = new PageRequest(start, limit, sort);
        Page<Order> page = orderService.list(pageable);
        model.addAttribute("page", page);
        return "order/index";
    }

    /**
     * 进入指派页面
     * @param id
     * @return
     */
    @GetMapping("/assign")
    public String assign(Long id, Model model){
        //调用服务层获取订单信息
        Order order = orderService.get(id);

        //将订单信息数据添加的model对象中
        model.addAttribute("order", order);

        //获取全部配送员信息
        List<Customer> distributors = customerService.listAllDistributor();
        model.addAttribute("distributors", distributors);

        return "order/assign";
    }

    /**
     * 执行指派操作
     * @param id
     * @param distributorId
     * @param model
     * @return
     */
    @PostMapping("/assign")
    public String assign(@RequestParam Long id,
                         @RequestParam Long distributorId,
                         HttpSession session,
                         Model model){
        //获取指派人信息
        Manager manager = (Manager) session.getAttribute("manager");
        //执行指派操作
        Order order = orderService.assign(id,manager.getId(), distributorId);

        //将订单信息数据添加的model对象中
        model.addAttribute("order", order);

        //添加提示信息
        model.addAttribute("tip", "指派成功");

        //跳转到订单详情页面
        return "order/detail";
    }

    /**
     * 订单详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam Long id, Model model){
        //调用服务层获取订单信息
        Order order = orderService.get(id);

        //将订单信息数据添加的model对象中
        model.addAttribute("order", order);

        //跳转到订单详情页面
        return "order/detail";
    }


}
