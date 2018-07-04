package com.wuyifeng.runner.core.service;

import com.wuyifeng.runner.core.domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     * @param customerId
     * @return
     */
    public Order create(Order order, Long customerId);

    /**
     * 订单全部列表（分页）
     *
     * @param pageable
     * @return
     */
    public Page<Order> list(Pageable pageable);

    /**
     * 通过ID获取订单信息
     *
     * @param id
     * @return
     */
    public Order get(Long id);


    /**
     * 由客户确认订单，订单状态改为5：已收货
     *
     * @param orderId
     * @return
     */
    public Order confirmByCustomer(Long orderId);

    /**
     * 评价订单，订单状态改为6：已评价
     *
     * @param evaluateContent
     * @return
     */
    public Order evaluate(Long orderId, String evaluateContent);

    /**
     * 配送员认领订单，订单状态改为3：配送中
     *
     * @param orderId
     * @return
     */
    public Order claim(Long orderId);

    /**
     * 由配送员确认收货，订单状态改为4：已送达
     *
     * @param orderId
     * @return
     */
    public Order confirmByDistributor(Long orderId);


    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    public Order modify(Order order);

    /**
     * 指派订单，订单状态改为2：已指派
     *
     * @param orderId    订单id
     * @param assignorId 由谁指派(管理员)
     * @param designeeId 指派给谁（配送员）
     * @return
     */
    public Order assign(Long orderId, Long assignorId, Long designeeId);

    //订单服务类：
    // 下单、订单列表（分页）、订单详情、确定订单（顾客）、评价订单、
    // 领取订单、确认订单（配送员），修改订单、指派订单

    /**
     * 根据客户ID获取订单列表
     *
     * @param customerId
     * @return
     */
    public Page<Order> listForCustomer(Long customerId, Pageable pageable);

    /**
     * 根据配送员ID获取订单列表
     *
     * @param distributorId
     * @return
     */
    public Page<Order> listForDistributor(Long distributorId, Pageable pageable);


}
