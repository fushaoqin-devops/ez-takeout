package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.entity.OrderDetail;
import com.shaoqin.ez_take_out.entity.Orders;
import com.shaoqin.ez_take_out.mapper.OrderDetailMapper;
import com.shaoqin.ez_take_out.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: OrderDetailServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/29/23 5:47 PM
 * Version 1.0
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Long id) {
        LambdaQueryWrapper<OrderDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderDetail::getOrderId, id);
        return this.list(lambdaQueryWrapper);
    }

}
