package com.example.shop_system.mapper;

import com.example.shop_system.entity.Favorite;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Select("SELECT * FROM favorite WHERE user_id = #{userId}")
    List<Favorite> findFavoritesByUser(Long userId);      // 获取用户的收藏列表
    @Insert("  INSERT INTO favorite (user_id, product_id, create_time) VALUES (#{userId}, #{productId}, NOW())")
    int addFavorite(Favorite favorite);                  // 添加收藏
    @Delete(" DELETE FROM favorite WHERE user_id = #{userId} AND product_id = #{productId}")
    int deleteFavorite(@Param("userId") Long userId,
                       @Param("productId") Long productId); // 删除收藏
}