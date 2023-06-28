package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.common.CustomException;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Category;
import com.shaoqin.ez_take_out.entity.Dish;
import com.shaoqin.ez_take_out.entity.Setmeal;
import com.shaoqin.ez_take_out.mapper.CategoryMapper;
import com.shaoqin.ez_take_out.service.CategoryService;
import com.shaoqin.ez_take_out.service.DishService;
import com.shaoqin.ez_take_out.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/26/23 7:57 PM
 * Version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public void remove(Long id, Long dishCount, Long setmealCount) {
        if (dishCount > 0) {
            throw new CustomException("Category is linked to existing dish, can not delete");
        }
        if (setmealCount > 0) {
            throw new CustomException("Category is linked to existing combo, can not delete");
        }

        super.removeById(id);
    }

    @Override
    public List<Category> getCategoryList(Category category) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public Page<Category> getCategoryPage(PageDto pageDto) {
        Page<Category> pageInfo = new Page<>(pageDto.getPage(), pageDto.getPageSize());

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Category::getSort);

        this.page(pageInfo, queryWrapper);

        return pageInfo;
    }

}
