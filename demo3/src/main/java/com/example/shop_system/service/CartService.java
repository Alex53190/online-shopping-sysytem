package com.example.shop_system.service;

import com.example.shop_system.entity.Cart;
import com.example.shop_system.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    @Autowired
    private  CartMapper cartMapper;

    // 获取用户购物车
    public List<Cart> getCartByUser(Long userId) {
        return cartMapper.findCartByUser(userId);
    }

    // 添加商品到购物车
    public void addToCart(Cart cart) {
        cartMapper.addToCart(cart);
    }

    // 更新购物车数量
    public void updateCartQuantity(Long id, int quantity) {
        cartMapper.updateCartQuantity(id, quantity);
    }

    // 删除购物车商品
    public void deleteFromCart(Long id) {
        cartMapper.deleteFromCart(id);
    }
}