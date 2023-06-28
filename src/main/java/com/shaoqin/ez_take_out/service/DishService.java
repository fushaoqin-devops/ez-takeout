package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.dto.DishDto;
import com.shaoqin.ez_take_out.entity.Dish;

/**
 * ClassName: DishService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/27/23 1:10 PM
 * Version 1.0
 */
public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);

}
