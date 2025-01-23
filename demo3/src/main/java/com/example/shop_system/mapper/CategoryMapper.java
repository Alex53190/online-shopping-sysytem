package com.example.shop_system.mapper;

import com.example.shop_system.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM category")
    List<Category> findAllCategories();         // 查询所有分类
    @Insert("INSERT INTO category (name, description) VALUES (#{name}, #{description})")
    int addCategory(Category category);         // 添加分类
    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteCategory(Long id);                // 删除分类
}