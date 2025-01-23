package com.example.shop_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    private Long userId;          // 用户 ID
    private Long merchantId;      // 商家 ID
    private BigDecimal totalPrice; // 总金额
    private String status;        // 状态：PENDING, SHIPPED, COMPLETED

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String remark;        // 备注
    private Date createTime;      // 创建时间

    public Date getCreateTime() {
        return createTime;
    }
}