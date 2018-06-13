package com.wuyifeng.runner.core.service.impl;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.domain.Manager;
import com.wuyifeng.runner.core.domain.Order;
import com.wuyifeng.runner.core.repository.OrderRepository;
import com.wuyifeng.runner.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order create(Order order, Long customerId) {
        //
        //Customer customer = customerRespository.findOne(customerId);
//      谁下单、哪个时间下单
        Customer customer = new Customer(customerId);

        order.setStatus(1);
        order.setCreator(customer);
        order.setCreateTime(new Date());
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> list(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order get(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public Order confirmByCustomer(Long orderId) {
//        由客户确认订单，订单状态改为5：已收货
        Order order = orderRepository.findOne(orderId);
        order.setStatus(5);
        order.setDeliveryTime(new Date());
        return orderRepository.save(order);
    }

    @Override
    public Order evaluate(Long orderId, String evaluateContent) {
        Order order = orderRepository.findOne(orderId);
        order.setStatus(6);
        order.setEvaluateTime(new Date());
        order.setEvaluateContent(evaluateContent);
        return orderRepository.save(order);
    }

    @Override
    public Order claim(Long orderId) {
        //配送员认领订单，订单状态改为3：配送中
        Order order = orderRepository.findOne(orderId);
        order.setStatus(3);
        order.setReceiptTime(new Date());
        return orderRepository.save(order);
    }

    @Override
    public Order confirmByDistributor(Long orderId) {
        //由配送员确认收货，订单状态改为4：已送达
        Order order = orderRepository.findOne(orderId);
        order.setStatus(4);
        order.setSendToTime(new Date());
        return orderRepository.save(order);
    }

    @Override
    public Order modify(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order assign(Long orderId, Long assignorId, Long designeeId) {
        Manager manager = new Manager(assignorId);

        Customer customer = new Customer(designeeId);

        //指派订单，订单状态改为2：已指派
        Order order = orderRepository.findOne(orderId);
        order.setStatus(2);
        order.setAssignor(manager);
        order.setDesignee(customer);
        order.setAssignTime(new Date());

        return orderRepository.save(order);
    }
}
