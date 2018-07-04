package com.wuyifeng.runner.admin.form;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
    @NotEmpty(message = "登录账号不能为空")
    private String username;

    @NotEmpty(message = "登录密码不能为空")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
