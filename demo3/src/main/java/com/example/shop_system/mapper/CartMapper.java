package com.example.shop_system.mapper;

import com.example.shop_system.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {
    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    List<Cart> findCartByUser(Long userId);                // 查询用户购物车
    @Insert("INSERT INTO cart (user_id, product_id, quantity) VALUES (#{userId}, #{productId}, #{quantity})")
    int addToCart(Cart cart);                              // 添加商品到购物车
    @Update("UPDATE cart SET quantity = #{quantity} WHERE id = #{id}")
    int updateCartQuantity(@Param("id") Long id,
                           @Param("quantity") int quantity); // 更新商品数量
    @Delete("DELETE FROM cart WHERE id = #{id}")
    int deleteFromCart(Long id);                          // 从购物车中删除商品
}