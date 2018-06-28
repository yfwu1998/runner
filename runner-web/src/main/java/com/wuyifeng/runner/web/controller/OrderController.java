package com.wuyifeng.runner.web.controller;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.domain.Order;
import com.wuyifeng.runner.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //进入下单页面
    @GetMapping("/index")
    public String index() {
        return "order/index";
    }

    /**
     * 进入帮我买下单页面
     *
     * @return
     */
    @GetMapping("/addForSell")
    public String addForSell() {
        return "order/addForSell";
    }

    /**
     * 执行帮我买下单操作，跳转到下单成功页面
     *
     * @return
     */
    @PostMapping("/addForSell")
    public String addForSell(@RequestParam String store,
                             @RequestParam String consignee,
                             @RequestParam String consigneeMobile,
                             @RequestParam String goods,
                             HttpSession session) {

        //1.order数据来自表单
        Order order = new Order();
        order.setStore(store);
        order.setConsignee(consignee);
        order.setConsigneeMobile(consigneeMobile);
        order.setGoods(goods);

        //2.下单客户ID，数据来源session
        Customer customer = (Customer) session.getAttribute("customer");

        //3.调用下单方法
        Order result = orderService.create(order, customer.getId());

        if (result != null){
            return "order/success";
        }else{
            return "publiz/error";
        }


    }

    /**
     * 进入帮我送下单页面
     *
     * @return
     */
    public String addForDeliver() {
        return null;
    }

    /**
     * 执行帮我送下单操作，跳转到下单成功页面
     *
     * @return
     */
    public String addForDeliver(String store, String consignee, String consigneeMobile, String goods) {
        return "order/success";
    }

    /**
     * 进入帮我取下单页面
     *
     * @return
     */
    public String addForTake() {
        return null;
    }

    /**
     * 执行帮我取下单操作，跳转到下单成功页面
     *
     * @return
     */
    public String addForTake(String store, String consignee, String consigneeMobile, String goods) {
        return "order/success";
    }
}
