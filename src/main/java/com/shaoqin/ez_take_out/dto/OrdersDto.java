package com.shaoqin.ez_take_out.dto;

import com.shaoqin.ez_take_out.entity.DishFlavor;
import com.shaoqin.ez_take_out.entity.OrderDetail;
import com.shaoqin.ez_take_out.entity.Orders;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: OrdersDto
 * Package: com.shaoqin.ez_take_out.dto
 * Description:
 * Author Shaoqin
 * Create 6/29/23 7:08 PM
 * Version 1.0
 */
@Data
public class OrdersDto extends Orders {

    private List<OrderDetail> orderDetails = new ArrayList<>();

}
