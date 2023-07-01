package com.shaoqin.ez_take_out.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.OrderPageDto;
import com.shaoqin.ez_take_out.dto.OrdersDto;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Orders;
import com.shaoqin.ez_take_out.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: OrderController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/29/23 5:48 PM
 * Version 1.0
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        orderService.submit(orders);
        return R.success("Order submitted");
    }

    @GetMapping("/userPage")
    public R<Page<OrdersDto>> getUserOrderPage(PageDto pageDto) {
        Page<OrdersDto> pageInfo = orderService.getUserOrderPage(pageDto);
        return R.success(pageInfo);
    }

    @GetMapping("/page")
    public R<Page<Orders>> getEmployeeOrderPage(OrderPageDto orderPageDto) {
        Page<Orders> pageInfo = orderService.getEmployeeOrderPage(orderPageDto);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(@RequestBody Orders orders) {
        orderService.updateById(orders);
        return R.success("Order updated");
    }

}
