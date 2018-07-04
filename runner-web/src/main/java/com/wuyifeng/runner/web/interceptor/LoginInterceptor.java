package com.wuyifeng.runner.web.interceptor;

import com.wuyifeng.runner.core.domain.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer!=null){
            return super.preHandle(request, response, handler);
        }else{
            response.sendRedirect("/publiz/login");
            return false;
        }

    }

}
