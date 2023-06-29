package com.shaoqin.ez_take_out.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.UserDto;
import com.shaoqin.ez_take_out.entity.User;
import com.shaoqin.ez_take_out.service.TwilioService;
import com.shaoqin.ez_take_out.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/28/23 1:55 PM
 * Version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpServletRequest request) {
        userService.verify(user, request.getSession());
        return R.success("Verification sent");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        User user = userService.login(userDto, request.getSession());
        return R.success(user);
    }

}
