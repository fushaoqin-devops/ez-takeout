package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.dto.DishDto;
import com.shaoqin.ez_take_out.entity.Dish;
import com.shaoqin.ez_take_out.entity.DishFlavor;
import com.shaoqin.ez_take_out.mapper.DishMapper;
import com.shaoqin.ez_take_out.service.DishFlavorService;
import com.shaoqin.ez_take_out.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: DishServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/27/23 1:11 PM
 * Version 1.0
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);

        Long dishId = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(flavor -> {
            flavor.setDishId(dishId);
            return flavor;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(lambdaQueryWrapper);

        // convert dish to dish dto to include flavors
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);

        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DishFlavor::getDishId, dishDto.getId());

        dishFlavorService.remove(lambdaQueryWrapper);

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(flavor -> {
            flavor.setDishId(dishDto.getId());
            return flavor;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    @Transactional
    public void updateStatus(Integer status, List<String> ids) {
        List<Dish> list = ids.stream().map(id -> {
            Dish dish = new Dish();
            dish.setId(Long.parseLong(id));
            dish.setStatus(status);
            return dish;
        }).toList();
        this.updateBatchById(list);
    }

    @Override
    @Transactional
    public void deleteDish(List<String> ids) {
        List<Long> list = ids.stream().map(Long::parseLong).toList();
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(DishFlavor::getDishId, list);
        this.removeBatchByIds(list);
        // remove dish flavors that's linked to the dishes
        dishFlavorService.remove(lambdaQueryWrapper);
    }

}
