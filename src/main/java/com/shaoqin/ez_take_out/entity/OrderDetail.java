package com.shaoqin.ez_take_out.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClassName: OrderDetail
 * Package: com.shaoqin.ez_take_out.entity
 * Description:
 * Author Shaoqin
 * Create 6/29/23 5:45 PM
 * Version 1.0
 */
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long orderId;

    private Long dishId;

    private Long setmealId;

    private String dishFlavor;

    private Integer number;

    private BigDecimal amount;

    private String image;

}
