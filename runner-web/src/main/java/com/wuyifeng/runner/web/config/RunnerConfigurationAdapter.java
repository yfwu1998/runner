package com.wuyifeng.runner.web.config;

import com.wuyifeng.runner.web.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class RunnerConfigurationAdapter extends WebMvcConfigurerAdapter {

   @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/order/*")
                .addPathPatterns("/customer/*");

        super.addInterceptors(registry);
    }
}
