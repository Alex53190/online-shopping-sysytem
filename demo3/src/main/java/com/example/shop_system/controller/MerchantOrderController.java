package com.example.shop_system.controller;

import com.example.shop_system.entity.Order;
import com.example.shop_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchant/orders")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
@RequiredArgsConstructor
public class MerchantOrderController {
    @Autowired
    private  OrderService orderService;

    // 查询商家订单列表
    @GetMapping
    public ResponseEntity<List<Order>> getOrdersByMerchant(@RequestParam Long merchantId) {
        List<Order> orders = orderService.getOrdersByMerchant(merchantId);
        return ResponseEntity.ok(orders);
    }

    // 更新订单状态
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok("Order status updated successfully");
    }

    // 更新订单备注
    @PutMapping("/{id}/remark")
    public ResponseEntity<String> updateOrderRemark(@PathVariable Long id, @RequestParam String remark) {
        orderService.updateOrderRemark(id, remark);
        return ResponseEntity.ok("Order remark updated successfully");
    }
}