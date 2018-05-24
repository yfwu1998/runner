package com.wuyifeng.runner.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "at_example")
public class Example {
    @Id
    @GeneratedValue
    private Long id;//主键ID，自增长

    @Column(length = 50)
    private String title;//中文标题

    private String remark;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

