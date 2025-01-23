package com.example.shop_system.service;


import com.example.shop_system.entity.Order;
import com.example.shop_system.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private  OrderMapper orderMapper;

    // 根据商家 ID 获取订单列表
    public List<Order> getOrdersByMerchant(Long merchantId) {
        return orderMapper.findOrdersByMerchant(merchantId);
    }

    // 更新订单状态
    public void updateOrderStatus(Long id, String status) {
        orderMapper.updateOrderStatus(id, status);
    }

    // 更新订单备注
    public void updateOrderRemark(Long id, String remark) {
        orderMapper.updateOrderRemark(id, remark);
    }
}
