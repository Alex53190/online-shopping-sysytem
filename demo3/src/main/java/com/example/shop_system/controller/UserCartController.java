package com.example.shop_system.controller;

import com.example.shop_system.entity.Cart;
import com.example.shop_system.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/cart")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
@RequiredArgsConstructor
public class UserCartController {
    @Autowired
    private  CartService cartService;

    // 获取用户购物车
    @GetMapping
    public ResponseEntity<List<Cart>> getCartByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }

    // 添加商品到购物车
    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
        cartService.addToCart(cart);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    // 更新购物车商品数量
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCartQuantity(@PathVariable Long id, @RequestParam int quantity) {
        cartService.updateCartQuantity(id, quantity);
        return ResponseEntity.ok("Cart quantity updated successfully");
    }

    // 删除购物车商品
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFromCart(@PathVariable Long id) {
        cartService.deleteFromCart(id);
        return ResponseEntity.ok("Product removed from cart successfully");
    }
}