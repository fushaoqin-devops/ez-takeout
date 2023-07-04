package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.dto.UserDto;
import com.shaoqin.ez_take_out.entity.User;
import jakarta.servlet.http.HttpSession;

/**
 * ClassName: UserService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/28/23 1:58 PM
 * Version 1.0
 */
public interface UserService extends IService<User> {
    public User login(UserDto userDto, HttpSession session);

    public void verify(User user);
}
