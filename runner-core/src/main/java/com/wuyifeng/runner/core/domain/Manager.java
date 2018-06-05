package com.wuyifeng.runner.core.domain;

import javax.persistence.*;

/**
 * 系统管理员实体类
 */
@Entity
@Table(name = "at_manager")
public class Manager {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 50)
    private String username;

    @Column(length = 50)
    private String nickname;

    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private String mobile;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
