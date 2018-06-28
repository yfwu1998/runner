package com.wuyifeng.runner.web.form;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderForTakeForm {

    @NotEmpty(message = "取货地址不能为空")
    private String pickupAddress;
    @NotEmpty(message = "收货人姓名不能为空")
    private String consignee;
    @NotEmpty(message = "收货人联系电话不能为空")
    private String consigneeMobile;
    @NotEmpty(message = "取货物品不能为空")
    private String goods;

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
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

