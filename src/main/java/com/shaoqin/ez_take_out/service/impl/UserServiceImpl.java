package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.common.CustomException;
import com.shaoqin.ez_take_out.dto.UserDto;
import com.shaoqin.ez_take_out.entity.User;
import com.shaoqin.ez_take_out.mapper.UserMapper;
import com.shaoqin.ez_take_out.service.TwilioService;
import com.shaoqin.ez_take_out.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/28/23 1:59 PM
 * Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private TwilioService twilioService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User login(UserDto userDto, HttpSession session) {
        String phone = userDto.getPhone();
        String code = userDto.getCode();
        if (StringUtils.isNotEmpty(phone) && StringUtils.isNotEmpty(code)) {
            phone = phone.replaceAll("[-()\\s]", "");
            phone = "+1" + phone;

            String verification = redisTemplate.opsForValue().get(phone);
            if (!StringUtils.isNotEmpty(verification)) throw new CustomException("Verification expired");
            boolean isValid = twilioService.checkVerificationCode(code, phone);
            if (!isValid) throw new CustomException("Invalid code");

            // save user if first time login
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getPhone, phone);
            User user = this.getOne(lambdaQueryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                this.save(user);
            }
            session.setAttribute("user", user.getId());
            redisTemplate.delete(phone);
            return user;
        }
        throw new CustomException("Invalid credentials");
    }

    @Override
    public void verify(User user) {
        String phone = user.getPhone();
        if (StringUtils.isEmpty(phone)) throw new CustomException("Failed to send verification");

        phone = phone.replaceAll("[-()\\s]", "");
        phone = "+1" + phone;
        twilioService.sendVerificationSMS(phone, "123");
        redisTemplate.opsForValue().set(phone, "VERIFICATION_PENDING", 5, TimeUnit.MINUTES);
    }

}
