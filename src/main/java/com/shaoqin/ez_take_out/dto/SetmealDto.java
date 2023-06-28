package com.shaoqin.ez_take_out.dto;

import com.shaoqin.ez_take_out.entity.Setmeal;
import com.shaoqin.ez_take_out.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * ClassName: SetmealDto
 * Package: com.shaoqin.ez_take_out.dto
 * Description:
 * Author Shaoqin
 * Create 6/28/23 2:28 AM
 * Version 1.0
 */
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;

}
