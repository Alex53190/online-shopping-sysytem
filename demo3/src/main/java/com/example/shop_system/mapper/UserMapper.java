package com.example.shop_system.mapper;

import com.example.shop_system.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    // 插入新用户
    @Insert("INSERT INTO users (id,username, password, email, role) VALUES (#{id},#{username}, #{password}, #{email}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    // 根据用户名查找用户
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    // 根据ID查找用户
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id);
}