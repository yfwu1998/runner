package com.wuyifeng.runner.core.service;

import com.wuyifeng.runner.core.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    public CustomerService customerService;

    @Test
    public void register() {
        Customer customer = new Customer();
        customer.setUsername("admin");
        customer.setNickname("系统管理员");
        customer.setPassword("123456");
        customer.setMobile("13902326789");
        Customer result = customerService.register(customer);
        Assert.assertNotNull(result);
    }

    @Test
    public void login() {
    }

    @Test
    public void list() {
    }

    @Test
    public void get() {
    }

    @Test
    public void update() {
    }

    @Test
    public void applyToDistributor() {
    }

    @Test
    public void confirmToDistributor() {
    }
}
