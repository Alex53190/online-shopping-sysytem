package com.example.shop_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Long id;
    private String content;  // 公告内容
    private Date createTime; // 创建时间

    public Long getId() {
        return id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getContent() {
        return content;
    }
}