package com.example.shop_system.mapper;

import com.example.shop_system.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM orders WHERE merchant_id = #{merchantId} ORDER BY create_time DESC")
    List<Order> findOrdersByMerchant(Long merchantId); // 根据商家 ID 查询订单
    @Update("UPDATE orders SET status = #{status} WHERE id = #{id}")
    int updateOrderStatus(@Param("id") Long id,
                          @Param("status") String status); // 更新订单状态
    @Update("UPDATE orders SET remark = #{remark} WHERE id = #{id}")
    int updateOrderRemark(@Param("id") Long id,
                          @Param("remark") String remark); // 更新订单备注
    @Select("SELECT * FROM orders WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<Order> findOrdersByUser(Long userId);             // 根据用户 ID 查询订单
    @Insert("INSERT INTO orders (user_id, merchant_id, total_price, status, remark, create_time)   VALUES (#{userId}, #{merchantId}, #{totalPrice}, 'PENDING', #{remark}, NOW())")
    int createOrder(Order order);                          // 创建订单
}