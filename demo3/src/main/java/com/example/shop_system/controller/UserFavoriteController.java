package com.example.shop_system.controller;

import com.example.shop_system.entity.Favorite;
import com.example.shop_system.service.FavoriteService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/favorites")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
@RequiredArgsConstructor
public class UserFavoriteController {
    @Autowired
    private  FavoriteService favoriteService;

    // 获取用户收藏列表
    @GetMapping
    public ResponseEntity<List<Favorite>> getFavoritesByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUser(userId));
    }

    // 添加收藏
    @PostMapping
    public ResponseEntity<String> addFavorite(@RequestBody Favorite favorite) {
        favoriteService.addFavorite(favorite);
        return ResponseEntity.ok("Favorite added successfully");
    }

    // 删除收藏
    @DeleteMapping
    public ResponseEntity<String> deleteFavorite(@RequestParam Long userId, @RequestParam Long productId) {
        favoriteService.deleteFavorite(userId, productId);
        return ResponseEntity.ok("Favorite deleted successfully");
    }
}