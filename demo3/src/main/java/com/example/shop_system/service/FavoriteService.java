package com.example.shop_system.service;

import com.example.shop_system.entity.Favorite;
import com.example.shop_system.mapper.FavoriteMapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    @Autowired
    private  FavoriteMapper favoriteMapper;

    // 获取用户收藏列表
    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteMapper.findFavoritesByUser(userId);
    }

    // 添加收藏
    public void addFavorite(Favorite favorite) {
        if(favorite.getUserId() != null&&favorite.getProductId()!=null){
        favoriteMapper.addFavorite(favorite);
    }
    }

    // 删除收藏
    public void deleteFavorite(Long userId, Long productId) {
        favoriteMapper.deleteFavorite(userId, productId);
    }
}