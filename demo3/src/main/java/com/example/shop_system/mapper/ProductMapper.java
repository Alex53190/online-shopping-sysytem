package com.example.shop_system.mapper;

import com.example.shop_system.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product WHERE merchant_id = #{merchantId} ORDER BY create_time DESC")
    List<Product> findProductsByMerchant(Long merchantId); // 根据商家 ID 查询商品列表
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findProductById(Long id);                      // 根据商品 ID 查询商品详情
    @Select("SELECT * FROM product WHERE category_id = #{categoryId} ORDER BY create_time DESC")
    List<Product> findProductsByCategory(Long categoryId); // 根据分类 ID 查询商品列表
    @Select("SELECT * FROM product ORDER BY create_time DESC")
    List<Product> findAllProducts();                       // 查询所有商品

    @Insert("INSERT INTO product (id,merchant_id, category_id, name, price, stock, description, create_time )  VALUES (#{id},#{merchantId}, #{categoryId}, #{name}, #{price}, #{stock}, #{description}, NOW())")
    int addProduct(Product product);                       // 添加商品

    @Update(" UPDATE product SET name = #{name}, price = #{price}, stock = #{stock}, category_id = #{categoryId},description = #{description} WHERE id = #{id}")
    int updateProduct(Product product);                    // 更新商品信息
    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteProduct(Long id);                            // 删除商品
}