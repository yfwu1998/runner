package com.wuyifeng.runner.web.form;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderForSellForm {
    @NotEmpty(message = "商店地址不能为空")
    private String store;
    @NotEmpty(message = "收货人姓名不能为空")
    private String consignee;
    @NotEmpty(message = "收货人联系电话不能为空")
    private String consigneeMobile;
    @NotEmpty(message = "购买商品不能为空")
    private String goods;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
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
