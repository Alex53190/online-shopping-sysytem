package com.example.shop_system.controller;

import com.example.shop_system.entity.Order;
import com.example.shop_system.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/orders")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
@RequiredArgsConstructor
public class UserOrderController {
    @Autowired
    private  UserOrderService userOrderService;

    // 查询用户订单列表
    @GetMapping
    public ResponseEntity<List<Order>> getOrdersByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(userOrderService.getOrdersByUser(userId));
    }

    // 创建订单
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        userOrderService.createOrder(order);
        return ResponseEntity.ok("Order created successfully");
    }
}