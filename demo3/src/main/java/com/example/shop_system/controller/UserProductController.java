package com.example.shop_system.controller;


import com.example.shop_system.entity.Product;
import com.example.shop_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/products")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
@RequiredArgsConstructor
public class UserProductController {
    @Autowired
    private  ProductService productService;

    // 根据分类查询商品列表
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    // 查询所有商品
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 根据商品 ID 查询商品详情
    @PostMapping("/getProduct")
    public ResponseEntity<Product> getProductById(@RequestBody Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}