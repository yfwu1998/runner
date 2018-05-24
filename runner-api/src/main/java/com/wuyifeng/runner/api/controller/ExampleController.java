package com.wuyifeng.runner.api.controller;

import com.wuyifeng.runner.core.domain.Example;
import com.wuyifeng.runner.core.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    //新增操作
    @PostMapping("/save")
    public String save(@RequestParam String title, @RequestParam String remark){
        Example example = new Example();
        example.setTitle(title);
        example.setRemark(remark);

        Example result = exampleService.save(example);
        return result.toString();
    }

    //查询操作
    //http://localhost:8088/api/example/{id}
    @GetMapping("/{id}")
    public String get(@PathVariable Long id){
        return exampleService.findById(id).toString();
    }
}
