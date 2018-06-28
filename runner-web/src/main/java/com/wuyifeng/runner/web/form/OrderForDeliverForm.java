package com.wuyifeng.runner.web.form;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderForDeliverForm {

    @NotEmpty(message = "送货人姓名不能为空")
    private String consigner;
    @NotEmpty(message = "送货人联系电话不能为空")
    private String consignerMobile;

    @NotEmpty(message = "收货人姓名不能为空")
    private String consignee;
    @NotEmpty(message = "收货人联系电话不能为空")
    private String consigneeMobile;
    @NotEmpty(message = "送货物品不能为空")
    private String goods;

    public String getConsigner() {
        return consigner;
    }

    public void setConsigner(String consigner) {
        this.consigner = consigner;
    }

    public String getConsignerMobile() {
        return consignerMobile;
    }

    public void setConsignerMobile(String consignerMobile) {
        this.consignerMobile = consignerMobile;
    }



    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}
