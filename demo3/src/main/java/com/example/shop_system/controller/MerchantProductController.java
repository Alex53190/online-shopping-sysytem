package com.example.shop_system.controller;

import com.example.shop_system.entity.Product;
import com.example.shop_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchant/products")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
@RequiredArgsConstructor
public class MerchantProductController {
    @Autowired
    private  ProductService productService;

    // 查询商家商品列表
    @GetMapping
    public ResponseEntity<List<Product>> getProductsByMerchant(@RequestParam Long merchantId) {
        List<Product> products = productService.getProductsByMerchant(merchantId);
        return ResponseEntity.ok(products);
    }

    // 根据商品 ID 获取商品详情
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // 添加新商品
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        if(product.getId()!=null&&product.getName()!=null&&product.getPrice()!=null&&product.getStock()!=null&&product.getMerchantId()!=null&&product.getCategoryId()!=null){
        productService.addProduct(product);}
        return ResponseEntity.ok("Product added successfully");
    }

    // 更新商品信息
    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok("Product updated successfully");
    }

    // 删除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}