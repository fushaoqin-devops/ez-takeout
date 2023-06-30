package com.shaoqin.ez_take_out.controller;

import com.shaoqin.ez_take_out.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: OrderDetailController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/29/23 5:48 PM
 * Version 1.0
 */
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

}
