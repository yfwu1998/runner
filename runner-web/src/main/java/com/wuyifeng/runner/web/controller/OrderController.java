package com.wuyifeng.runner.web.controller;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.domain.Order;
import com.wuyifeng.runner.core.service.OrderService;
import com.wuyifeng.runner.web.form.OrderForDeliverForm;
import com.wuyifeng.runner.web.form.OrderForSellForm;
import com.wuyifeng.runner.web.form.OrderForTakeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public String addForSell(@Validated OrderForSellForm orderForSellForm,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession session) {

        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            model.addAttribute("errorMsg", errorMsg);
            return "order/addForSell";
        }

        //1.order数据来自表单
        Order order = new Order();
        order.setStore(orderForSellForm.getStore());
        order.setConsignee(orderForSellForm.getConsignee());
        order.setConsigneeMobile(orderForSellForm.getConsigneeMobile());
        order.setGoods(orderForSellForm.getGoods());
        order.setType(1);
        //2.下单客户ID，数据来源session
        Customer customer = (Customer) session.getAttribute("customer");

        //3.调用下单方法
        Order result = orderService.create(order, customer.getId());

        if (result != null) {
            return "order/success";
        } else {
            return "publiz/error";
        }


    }

    /**
     * 进入帮我送下单页面
     *
     * @return
     */
    @GetMapping("/addForDeliver")
    public String addForDeliver() {
        return "order/addForDeliver";
    }

    /**
     * 执行帮我送下单操作，跳转到下单成功页面
     *
     * @return
     */
    @PostMapping("/addForDeliver")
    public String addForDeliver(@Validated OrderForDeliverForm orderForDeliverForm,
                                BindingResult bindingResult,
                                Model model,
                                HttpSession session) {

        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            model.addAttribute("errorMsg", errorMsg);
            return "order/addForDeliver";
        }

        //1.order数据来自表单
        Order order = new Order();

        order.setConsigner(orderForDeliverForm.getConsigner());
        order.setConsignerMobile(orderForDeliverForm.getConsignerMobile());
        order.setConsignee(orderForDeliverForm.getConsignee());
        order.setConsigneeMobile(orderForDeliverForm.getConsigneeMobile());
        order.setGoods(orderForDeliverForm.getGoods());
        order.setType(2);

        //2.下单客户ID，数据来源session
        Customer customer = (Customer) session.getAttribute("customer");

        //3.调用下单方法
        Order result = orderService.create(order, customer.getId());

        if (result != null) {
            return "order/success";
        } else {
            return "publiz/error";
        }


    }


    /**
     * 进入帮我取下单页面
     *
     * @return
     */
    @GetMapping("/addForTake")
    public String addForTake() {
        return "order/addForTake";
    }

    /**
     * 执行帮我取下单操作，跳转到下单成功页面
     *
     * @return
     */
    @PostMapping("/addForTake")
    public String addForTake(@Validated OrderForTakeForm orderForTakeForm,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession session) {

        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            model.addAttribute("errorMsg", errorMsg);
            return "order/addForSell";
        }

        //1.order数据来自表单
        Order order = new Order();
        order.setPickupAddress(orderForTakeForm.getPickupAddress());
        order.setConsignee(orderForTakeForm.getConsignee());
        order.setConsigneeMobile(orderForTakeForm.getConsigneeMobile());
        order.setGoods(orderForTakeForm.getGoods());
        order.setType(3);

        //2.下单客户ID，数据来源session
        Customer customer = (Customer) session.getAttribute("customer");

        //3.调用下单方法
        Order result = orderService.create(order, customer.getId());

        if (result != null) {
            return "order/success";
        } else {
            return "publiz/error";
        }


    }

    /**
     * 获取当前用户订单列表分页
     *
     * @param start 起始位置
     * @param limit 每页显示多少条记录
     * @param model
     * @return
     */
    @GetMapping("/lists")
    public String lists(@RequestParam(defaultValue = "0", value = "start") Integer start,
                        @RequestParam(defaultValue = "2", value = "limit") Integer limit,
                        HttpSession session, Model model) {

        Customer customer = (Customer) session.getAttribute("customer");
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
        Pageable pageable = new PageRequest(start, limit, sort);
        Page<Order> page = orderService.listForCustomer(customer.getId(), pageable);
        model.addAttribute("page", page);
        return "order/lists";
    }

    /**
     * 获取当前配送员配送订单列表
     *
     * @param start
     * @param limit
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/lists2")
    public String listsForDistributor(@RequestParam(defaultValue = "0", value = "start") Integer start,
                                      @RequestParam(defaultValue = "2", value = "limit") Integer limit,
                                      HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
        Pageable pageable = new PageRequest(start, limit, sort);
        Page<Order> page = orderService.listForDistributor(customer.getId(), pageable);
        model.addAttribute("page", page);
        return "order/lists2";
    }


    @GetMapping("/detail")
    public String detail(@RequestParam Long id, Model model) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        return "order/detail";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam Long id, Model model) {

        //1.先去调用顾客确认方法
        orderService.confirmByCustomer(id);

        //2.再去查询订单信息，跳转到详情页面
        Order order = orderService.get(id);
        model.addAttribute("tip", "您已确认收货，欢迎继续使用");
        model.addAttribute("order", order);
        return "order/detail";
    }

    //进入评价页面
    @GetMapping("/evaluate")
    public String evaluate(@RequestParam Long id, Model model) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        return "order/evaluate";
    }

    //执行评价操作
    @PostMapping("/evaluate")
    public String evaluate(@RequestParam Long id, @RequestParam String evaluateContent, Model model) {
        //0.TODO:空值检查,检查不通过，跳转到评价页面

        //1.执行评价操作
        orderService.evaluate(id, evaluateContent);

        //2.再去查询订单信息，跳转到详情页面
        Order order = orderService.get(id);
        model.addAttribute("tip", "您已评价成功，欢迎继续使用");
        model.addAttribute("order", order);
        return "order/detail";
    }

    /**
     * 配送员确认送达订单
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/sendTo")
    public String sendTo(@RequestParam Long id, Model model){
        orderService.confirmByDistributor(id);
        //2.再去查询订单信息，跳转到详情页面
        Order order = orderService.get(id);
        model.addAttribute("tip", "您已成功送达，请及时联系收货人！");
        model.addAttribute("order", order);
        return "order/detail";
    }

    /**
     * 配送员领取订单
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/receipt")
    public String receipt(@RequestParam Long id, Model model){
        orderService.claim(id);
        //2.再去查询订单信息，跳转到详情页面
        Order order = orderService.get(id);
        model.addAttribute("tip", "您领取订单，请注意安全配送！");
        model.addAttribute("order", order);
        return "order/detail";
    }

    private String getErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        int i = 0;
        for (ObjectError error : bindingResult.getAllErrors()) {
            if (i != 0) {
                errorMsg.append("<br/>");
            }
            errorMsg.append(error.getDefaultMessage());
            i++;
        }
        return errorMsg.toString();
    }
}
