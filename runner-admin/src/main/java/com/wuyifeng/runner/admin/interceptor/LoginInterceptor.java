package com.wuyifeng.runner.admin.interceptor;

import com.wuyifeng.runner.core.domain.Manager;
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
        Manager manager= (Manager) session.getAttribute("manager");
        if (manager!=null){
            return super.preHandle(request, response, handler);
        }else{
            response.sendRedirect("/publiz/login");
            return false;
        }

    }

}
