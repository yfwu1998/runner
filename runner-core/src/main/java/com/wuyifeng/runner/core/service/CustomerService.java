package com.wuyifeng.runner.core.service;

import com.wuyifeng.runner.core.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    /**
     * 顾客注册
     * @param customer
     * @return
     */
    public Customer register(Customer customer);

    /**
     * 顾客登录
     * @param username
     * @param password
     * @return
     */
    public Customer login(String username, String password);

    /**
     * 顾客列表（分页）
     * @param pageable
     * @return
     */

    public Page<Customer> list(Pageable pageable);

    /**
     * 通过顾客ID获取顾客信息
     * @param id
     * @return
     */
    public Customer get(Long id);

    /**
     * 修改顾客
     * @param customer
     * @return
     */
    public Customer update(Customer customer);

    /**
     * 申请配送资格
     * @param id
     * @return
     */
    public Customer applyToDistributor(Long id);

    /**
     * 确认配送资格
     * @param id
     * @param status(2:通过，9：不通过)
     * @return
     */
    public Customer confirmToDistributor(Long id, Integer status) throws Exception;


    /**
     * 获取所有配送员信息
     * @return
     */
    public List<Customer> listAllDistributor();

//    注册、登录、顾客列表（分页）、顾客详情、修改顾客，申请配送资格，确认配送资格


}
