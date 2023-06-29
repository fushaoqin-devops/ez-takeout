package com.shaoqin.ez_take_out.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.DishDto;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Dish;
import com.shaoqin.ez_take_out.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        dishService.saveWithFlavor(dishDto);
        return R.success("Dish added");
    }

    @GetMapping("/page")
    public R<Page<DishDto>> page(PageDto pageDto) {
        Page<DishDto> dishDtoPage = dishService.getDishPage(pageDto);
        return R.success(dishDtoPage);
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
    public R<String> changeStatus(@PathVariable("status") Integer status, @RequestParam("ids") List<Long> ids) {
        dishService.updateStatus(status, ids);
        return R.success("Status updated");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam("ids") List<Long> ids) {
        dishService.deleteDish(ids);
        return R.success("Dish deleted");
    }

    @GetMapping("/list")
    public R<List<DishDto>> list(DishDto dish) {
        List<DishDto> list = dishService.getDishByCategoryId(dish);
        return R.success(list);
    }

}
