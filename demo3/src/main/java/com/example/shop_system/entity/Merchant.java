package com.example.shop_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    private Long id;
    private Long userId;       // 用户 ID
    private String merchantName; // 商家名称
    private String contactInfo;  // 联系方式
    private String status;       // 状态：PENDING, APPROVED, REJECTED

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getStatus() {
        return status;
    }
}