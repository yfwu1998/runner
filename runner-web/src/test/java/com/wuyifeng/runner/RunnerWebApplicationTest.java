package com.wuyifeng.runner;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.domain.Manager;
import com.wuyifeng.runner.core.domain.Order;
import com.wuyifeng.runner.core.service.CustomerService;
import com.wuyifeng.runner.core.service.ManagerService;
import com.wuyifeng.runner.core.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 初始化数据，创建完数据库后执行该测试类即可。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RunnerWebApplicationTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private OrderService orderService;

    @Test
    public void initData(){

        //普通顾客
        Customer customer = new Customer();
        customer.setUsername("1001");
        customer.setNickname("顾客1");
        customer.setPassword("123456");
        customer.setMobile("13902326789");
        Customer customerResult = customerService.register(customer);

        //配送员
        Customer customer2 = new Customer();
        customer2.setUsername("1002");
        customer2.setNickname("顾客2");
        customer2.setPassword("123456");
        customer2.setMobile("13902326789");
        customer2.setApplyStatus(1);
        customer2.setType(2);
        Customer customerResult2 = customerService.register(customer2);

        //管理员
        Manager manager = new Manager();
        manager.setUsername("admin");
        manager.setNickname("后台管理员");
        manager.setPassword("123456");
        manager.setMobile("123456789");
        Manager managerResult = managerService.create(manager);

        //订单信息
        Order order = new Order();
        order.setPickupAddress("取货地址");
        order.setConsignee("张三");
        order.setConsigneeMobile("123456789");
        order.setGoods("要取的物品");
        order.setType(3);

        //订单正常流程
        //1.顾客下单
        Order orderResult = orderService.create(order, customerResult.getId());

        //2.管理员指派
        Order orderResult2 = orderService.assign(orderResult.getId(), managerResult.getId(), customerResult2.getId());

        //3.配送员领取
        Order orderResult3 = orderService.claim(orderResult2.getId());

        //4.配送员送达
        Order orderResult4 = orderService.confirmByDistributor(orderResult3.getId());

        //5.顾客确认收货
        Order orderResult5 = orderService.confirmByCustomer(orderResult4.getId());

        //6.顾客评价
        Order orderResult6 = orderService.evaluate(orderResult5.getId(), "这是我的评价");

        Assert.assertNotNull(orderResult6);
    }

    @Test
    public void testAddManager(){
       //管理员
        Manager manager = new Manager();
        manager.setUsername("admin");
        manager.setNickname("后台管理员");
        manager.setPassword("123456");
        manager.setMobile("123456789");
        Manager managerResult = managerService.create(manager);
        Assert.assertNotNull(manager);
    }
}