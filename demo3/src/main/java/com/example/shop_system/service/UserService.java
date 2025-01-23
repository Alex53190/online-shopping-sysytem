package com.example.shop_system.service;

import com.example.shop_system.entity.User;
import com.example.shop_system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 用户注册
    public boolean register(User user) {
//      //   检查用户名是否存在
//        if (userMapper.findByUsername(user.getUsername()) != null) {
//            return false;  // 用户名已存在
//        }
         //插入用户
        user.setRole("USER");  // 默认用户角色为普通用户
        userMapper.insertUser(user);
        return true;
    }

    // 用户登录
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // 登录成功
        }
        return null;  // 登录失败
    }

    // 根据ID查找用户
    public User findById(Long id) {
        return userMapper.findById(id);
    }
    // 根据用户名查找用户
    public User findByUsername(String username) {
        if(username != null) {
            return userMapper.findByUsername(username);
        }
        return null;
    }
}