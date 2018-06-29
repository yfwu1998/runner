package com.wuyifeng.runner.core.service;

import com.wuyifeng.runner.core.domain.Manager;
import com.wuyifeng.runner.core.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ManagerService managerService;
    @Test
    public void create() {
    }

    @Test
    public void list() {
    }

    @Test
    public void get() {
    }

    @Test
    public void confirmByCustomer() {
    }

    @Test
    public void evaluate() {
    }

    @Test
    public void claim() {
    }

    @Test
    public void confirmByDistributor() {
    }

    @Test
    public void modify() {
    }

    @Test
    public void assign() {

        Manager manager = managerService.get(1L);

        Order order = orderService.get(1L);
        order.setAssignor(manager);
        order.setAssignTime(new Date());

        orderService.modify(order);
    }
}