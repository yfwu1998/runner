package com.wuyifeng.runner.core.repository;

import com.wuyifeng.runner.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order , Long>{
}
