package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.dto.OrderPageDto;
import com.shaoqin.ez_take_out.dto.OrdersDto;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Orders;

/**
 * ClassName: OrderService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/29/23 5:46 PM
 * Version 1.0
 */
public interface OrderService extends IService<Orders> {

    void submit(Orders orders);

    Page<OrdersDto> getUserOrderPage(PageDto pageDto);

    Page<Orders> getEmployeeOrderPage(OrderPageDto orderPageDto);

}
