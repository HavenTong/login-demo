package com.haven.logindemo.controller;

import com.haven.logindemo.domain.User;
import com.haven.logindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HavenTong
 * @date 2019/11/19 8:00 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/sendCode")
    public String sendCheckedCode(@RequestParam("email") String email){
        userService.sendCheckCode(email);
        return "success";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user, @RequestParam("checkCode") String checkCode){
        userService.register(user, checkCode);
        return "register success";
    }



}
