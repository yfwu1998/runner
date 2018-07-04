package com.wuyifeng.runner.core.service.impl;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.repository.CustomerRepository;
import com.wuyifeng.runner.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Transient
    @Override
    public Customer register(Customer customer) {
        customer.setRegistTime(new Date());
        return customerRepository.save(customer);
    }

    @Override
    public Customer login(String username, String password) {
        return customerRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Page<Customer> list(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer get(Long id) {
        return customerRepository.getOne(id);
    }

    @Transient
    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transient
    @Override
    public Customer applyToDistributor(Long id) {

        //通过id获取顾客信息
        Customer customer = customerRepository.findOne(id);
        //修改申请状态
        customer.setApplyStatus(1);
        //保存修改
        return customerRepository.save(customer);
    }

    @Transient
    @Override
    public Customer confirmToDistributor(Long id, Integer status) throws Exception {
        //通过id获取顾客信息
        Customer customer = customerRepository.findOne(id);
        //修改申请状态
        if (status == 2){
            customer.setApplyStatus(status);
            customer.setType(2);
        }else if(status == 9){
            customer.setApplyStatus(status);

        }else{
            throw new Exception("参数错误");
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> listAllDistributor() {
        return customerRepository.findByType(2);
    }
}
