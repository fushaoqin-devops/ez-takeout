package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.entity.OrderDetail;

import java.util.List;

/**
 * ClassName: OrderDetailService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/29/23 5:47 PM
 * Version 1.0
 */
public interface OrderDetailService extends IService<OrderDetail> {

    List<OrderDetail> getOrderDetailsByOrderId(Long id);

}
