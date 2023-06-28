package com.shaoqin.ez_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.DishDto;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Category;
import com.shaoqin.ez_take_out.entity.Dish;
import com.shaoqin.ez_take_out.service.CategoryService;
import com.shaoqin.ez_take_out.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: DishController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/27/23 2:31 PM
 * Version 1.0
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        dishService.saveWithFlavor(dishDto);
        return R.success("Dish added");
    }

    @GetMapping("/page")
    public R<Page> page(PageDto pageDto) {
        Page<Dish> pageInfo = new Page<>(pageDto.getPage(), pageDto.getPageSize());
        Page<DishDto> dishDtoPageInfo = new Page<>();

        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(pageDto.getName() != null, Dish::getName, pageDto.getName());
        lambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);

        dishService.page(pageInfo, lambdaQueryWrapper);

        BeanUtils.copyProperties(pageInfo, dishDtoPageInfo, "records");

        // convert original list of dish to list of dish dto to include category name
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map(record -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(record, dishDto);
            Long categoryId = record.getCategoryId();
            Category category = categoryService.getById(categoryId);
            dishDto.setCategoryName(category.getName());
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPageInfo.setRecords(list);

        return R.success(dishDtoPageInfo);
    }

    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable("id") Long id) {
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        dishService.updateWithFlavor(dishDto);

        return R.success("Dish updated");
    }

    @PostMapping("/status/{status}")
    public R<String> changeStatus(@PathVariable("status") Integer status, @RequestParam("ids") List<String> ids) {
        dishService.updateStatus(status, ids);

        return R.success("Status updated");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam("ids") List<String> ids) {
        dishService.deleteDish(ids);

        return R.success("Dish deleted");
    }

}
