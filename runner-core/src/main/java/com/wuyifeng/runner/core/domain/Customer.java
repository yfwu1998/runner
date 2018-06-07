package com.wuyifeng.runner.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 顾客（配送员）实体类
 */
@Entity
@Table(name = "at_customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    //用户名
    @Column(length = 50, unique = true)
    private String username;

    //昵称
    @Column(length = 50)
    private String nickname;
    // 密码
    @Column(length = 50)
    private String password;

    // 员工类型（1：普通顾客，2：配送员）
    private Integer type = 1;

    private Integer applyStatus = 0;//申请状态（0：默认没有申请，1：申请中，2：通过申请，9：不通过）
    // 手机号码
    @Column(length = 50)
    private String mobile;

    //用户名、昵称、密码、用户类型（1：普通顾客，2：配送员）、手机号码、注册日期

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date registTime;

    private Integer status = 1;//数据状态(-1:删除，0：禁用，1：正常),状态删除标识


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
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

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }
}