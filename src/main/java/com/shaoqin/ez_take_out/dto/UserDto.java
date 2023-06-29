package com.shaoqin.ez_take_out.dto;

import com.shaoqin.ez_take_out.entity.User;
import lombok.Data;

/**
 * ClassName: UserDto
 * Package: com.shaoqin.ez_take_out.dto
 * Description:
 * Author Shaoqin
 * Create 6/28/23 5:17 PM
 * Version 1.0
 */
@Data
public class UserDto extends User {
    private String code;
}
