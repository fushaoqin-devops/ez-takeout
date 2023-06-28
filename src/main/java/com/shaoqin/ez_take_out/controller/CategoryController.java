package com.shaoqin.ez_take_out.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Category;
import com.shaoqin.ez_take_out.service.CategoryService;
import com.shaoqin.ez_take_out.service.DishService;
import com.shaoqin.ez_take_out.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/26/23 7:58 PM
 * Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @PostMapping
    public R<String> save(@RequestBody Category category) {
        categoryService.save(category);
        return R.success("Category added");
    }

    @GetMapping("/page")
    public R<Page<Category>> page(PageDto pageDto) {
        Page<Category> pageInfo = categoryService.getCategoryPage(pageDto);
        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> delete(@RequestParam("id") Long id) {
        long dishCount = dishService.getDishCountByCategoryId(id);
        long setmealCount = setmealService.getSetmealCountByCategoryId(id);
        categoryService.remove(id, dishCount, setmealCount);
        return R.success("Category deleted");
    }

    @PutMapping
    public R<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("Category updated");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        List<Category> list = categoryService.getCategoryList(category);
        return R.success(list);
    }

}
