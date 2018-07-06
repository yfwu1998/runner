package com.wuyifeng.runner.admin.form;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {

    @NotEmpty(message = "登录账号不能为空")
    private String username;
    @NotEmpty(message = "用户昵称不能为空")
    private String nickname;
    @NotEmpty(message = "登录密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
