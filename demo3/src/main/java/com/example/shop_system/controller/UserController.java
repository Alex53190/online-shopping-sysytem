package com.example.shop_system.controller;


import com.example.shop_system.entity.User;
import com.example.shop_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        boolean success = userService.register(user);
        if (success) {
            return "注册成功";
        } else {
            return "用户名已存在";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
          //  session.setAttribute("user", loggedInUser);
            return "登录成功";
        } else {
            return "用户名或密码错误";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "登出成功";
    }

    @GetMapping("/getRole")
    public String getuserRole(@RequestParam String username) {
        User user=userService.findByUsername(username);
        String role="USER";
        if (user != null) {
             role = user.getRole();
            // 使用 role 做进一步处理
        } else {
            // 处理 user 为 null 的情况，例如记录日志、抛出异常或返回默认值
        }

        return  role;
        }
//    @PostMapping("/getId")
//    public long getuserId(@RequestBody String username) {
//        User user=userService.findByUsername(username);
//        Long Id= 100L;
//        if (user != null) {
//            Id = user.getId();
//            // 使用 role 做进一步处理
//        } else {
//            // 处理 user 为 null 的情况，例如记录日志、抛出异常或返回默认值
//        }
//
//        return  Id;
//    }
//    @GetMapping("/current")
//    public User getCurrentUser(HttpSession session) {
//        return (User) session.getAttribute("user");
//    }
}