package com.shaoqin.ez_take_out.dto;

import com.shaoqin.ez_take_out.entity.Dish;
import com.shaoqin.ez_take_out.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DishDto
 * Package: com.shaoqin.ez_take_out.dto
 * Description:
 * Author Shaoqin
 * Create 6/27/23 2:44 PM
 * Version 1.0
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

    private boolean withFlavors;

}
