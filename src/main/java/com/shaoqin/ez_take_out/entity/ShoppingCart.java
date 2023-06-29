package com.shaoqin.ez_take_out.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: ShoppingCart
 * Package: com.shaoqin.ez_take_out.entity
 * Description:
 * Author Shaoqin
 * Create 6/29/23 3:27 PM
 * Version 1.0
 */
@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long userId;

    private Long dishId;

    private Long setmealId;

    private String dishFlavor;

    private Integer number;

    private BigDecimal amount;

    private String image;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
