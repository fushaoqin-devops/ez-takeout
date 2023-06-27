package com.shaoqin.ez_take_out.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ClassName: Category
 * Package: com.shaoqin.ez_take_out.entity
 * Description:
 * Author Shaoqin
 * Create 6/26/23 7:54 PM
 * Version 1.0
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // type 1 dish category 2 combo category
    private Integer type;

    // category name
    private String name;

    // category order
    private Integer sort;

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
