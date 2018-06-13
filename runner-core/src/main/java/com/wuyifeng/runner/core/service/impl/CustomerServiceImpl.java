package com.wuyifeng.runner.core.service.impl;

import com.wuyifeng.runner.core.domain.Customer;
import com.wuyifeng.runner.core.repository.CustomerRepository;
import com.wuyifeng.runner.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer register(Customer customer) {
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

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer applyToDistributor(Long id) {

        //通过id获取顾客信息
        Customer customer = customerRepository.findOne(id);
        //修改申请状态
        customer.setApplyStatus(1);
        //保存修改
        return customerRepository.save(customer);
    }

    @Override
    public Boolean confirmToDistributor(Long id, Integer status) throws Exception {
        //通过id获取顾客信息
        Customer customer = customerRepository.findOne(id);
        //修改申请状态
        if (status == 2){
            customer.setApplyStatus(status);
            customer.setType(2);
            return true;
        }else if(status == 9){
            customer.setApplyStatus(status);
            return false;

        }else{
            throw new Exception("参数错误");
        }
    }
}
