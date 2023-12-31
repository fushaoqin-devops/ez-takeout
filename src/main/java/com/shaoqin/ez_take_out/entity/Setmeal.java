package com.shaoqin.ez_take_out.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: SetmealDish
 * Package: com.shaoqin.ez_take_out.entity
 * Description:
 * Author Shaoqin
 * Create 6/27/23 1:07 PM
 * Version 1.0
 */
@Data
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long categoryId;

    private String name;

    private BigDecimal price;

    //status 0:disabled 1: enabled
    private Integer status;

    private String code;

    private String description;

    private String image;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @TableLogic
    private Integer isDeleted;

}
