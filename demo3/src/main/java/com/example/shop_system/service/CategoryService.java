package com.example.shop_system.service;

import com.example.shop_system.entity.Category;
import com.example.shop_system.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private  CategoryMapper categoryMapper;

    // 查询所有分类
    public List<Category> getAllCategories() {
        return categoryMapper.findAllCategories();
    }

    // 添加分类
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }

    // 删除分类
    public void deleteCategory(Long id) {
        categoryMapper.deleteCategory(id);
    }
}