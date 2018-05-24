package com.wuyifeng.runner.core.repository;

import com.wuyifeng.runner.core.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {
}

