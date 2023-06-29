package com.shaoqin.ez_take_out.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: User
 * Package: com.shaoqin.ez_take_out.entity
 * Description:
 * Author Shaoqin
 * Create 6/28/23 1:57 PM
 * Version 1.0
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

    private String avatar;

    private Integer status;

}
