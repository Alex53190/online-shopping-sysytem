package com.example.shop_system.service;

import com.example.shop_system.entity.Order;
import com.example.shop_system.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOrderService {
    @Autowired
    private  OrderMapper orderMapper;

    // 查询用户订单
    public List<Order> getOrdersByUser(Long userId) {
        return orderMapper.findOrdersByUser(userId);
    }

    // 创建订单
    public void createOrder(Order order) {
        orderMapper.createOrder(order);
    }
}