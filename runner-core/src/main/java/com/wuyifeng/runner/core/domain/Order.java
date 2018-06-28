package com.wuyifeng.runner.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单实体类
 */
@Entity
@Table(name = "at_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    //收货人姓名
    @Column(length = 50)
    private String consignee;
    // 收货人电话
    @Column(length = 50)
    private String consigneeMobile;
    // 物品（取、买、送）
    private String goods;
    // 取货地址
    private String pickupAddress;
    // 商店、
    @Column(length = 50)
    private String store;
    // 送货人电话、
    @Column(length = 50)
    private String consignerMobile;
    // 送货人姓名、
    @Column(length = 50)
    private String consigner;

    private Integer type;//1：帮我买，2：帮我送，3：帮我取

    // 谁下单
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Customer creator;
    // 哪个时间下单
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;
    // 谁指派
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assignor_id")
    private Manager assignor;
    // 哪个时间指派
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date assignTime;
    // 指派给谁
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "designee_id")
    private Customer designee;

    // 什么时候领取
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date receiptTime;
    //什么时候送达
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date sendToTime;

    //什么时候确认收货
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deliveryTime;
    //什么时候评价
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date evaluateTime;
    // 评价内容
    private String evaluateContent;
    // 状态（1：下单成功、2：已指派、3：配送中、4：已送达、5：已收货、6：已评价，-1：删除）
    public Integer status = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getConsignerMobile() {
        return consignerMobile;
    }

    public void setConsignerMobile(String consignerMobile) {
        this.consignerMobile = consignerMobile;
    }

    public String getConsigner() {
        return consigner;
    }

    public void setConsigner(String consigner) {
        this.consigner = consigner;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Customer getCreator() {
        return creator;
    }

    public void setCreator(Customer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Manager getAssignor() {
        return assignor;
    }

    public void setAssignor(Manager assignor) {
        this.assignor = assignor;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Customer getDesignee() {
        return designee;
    }

    public void setDesignee(Customer designee) {
        this.designee = designee;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public Date getSendToTime() {
        return sendToTime;
    }

    public void setSendToTime(Date sendToTime) {
        this.sendToTime = sendToTime;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
