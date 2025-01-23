package com.example.shop_system.service;


import com.example.shop_system.entity.Product;
import com.example.shop_system.mapper.ProductMapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private   ProductMapper productMapper;

    // 根据商家 ID 获取商品列表
    public List<Product> getProductsByMerchant(Long merchantId) {
        return productMapper.findProductsByMerchant(merchantId);
    }

    // 根据商品 ID 获取商品详情
    public Product getProductById(Long id) {
        return productMapper.findProductById(id);
    }

    // 根据分类 ID 获取商品列表
    public List<Product> getProductsByCategory(Long categoryId) {
        return productMapper.findProductsByCategory(categoryId);
    }

    // 获取所有商品
    public List<Product> getAllProducts() {
        return productMapper.findAllProducts();
    }

    // 添加商品
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    // 更新商品信息
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    // 删除商品
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }
}