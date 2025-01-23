package com.example.shop_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    private Long id;
    private Long userId;       // 用户 ID

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long productId;    // 商品 ID
    private Date createTime;   // 收藏时间

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getId() {
        return id;
    }
}