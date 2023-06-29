package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.common.CustomException;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.dto.SetmealDto;
import com.shaoqin.ez_take_out.entity.Category;
import com.shaoqin.ez_take_out.entity.Setmeal;
import com.shaoqin.ez_take_out.entity.SetmealDish;
import com.shaoqin.ez_take_out.mapper.SetmealMapper;
import com.shaoqin.ez_take_out.service.CategoryService;
import com.shaoqin.ez_take_out.service.SetmealDishService;
import com.shaoqin.ez_take_out.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: SetmealServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/27/23 1:15 PM
 * Version 1.0
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Long getSetmealCountByCategoryId(Long id) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        return this.count(setmealLambdaQueryWrapper);
    }

    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream()
                .peek(setmealDish -> setmealDish.setSetmealId(setmealDto.getId()))
                .collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public Page<SetmealDto> getSetmealPage(PageDto pageDto) {
        Page<Setmeal> setmealPage = new Page<>(pageDto.getPage(), pageDto.getPageSize());
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(pageDto.getName() != null, Setmeal::getName, pageDto.getName());
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);
        this.page(setmealPage, lambdaQueryWrapper);

        Page<SetmealDto> setmealDtoPage = new Page<>();
        BeanUtils.copyProperties(setmealPage, setmealDtoPage, "records");

        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDto> list = records.stream().map(record -> {
            Long categoryId = record.getCategoryId();
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(record, setmealDto);
            Category category = categoryService.getById(categoryId);
            if (category != null) setmealDto.setCategoryName(category.getName());
            return setmealDto;
        }).collect(Collectors.toList());

        setmealDtoPage.setRecords(list);

        return setmealDtoPage;
    }

    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId, ids).eq(Setmeal::getStatus, 1);
        long count = this.count(setmealLambdaQueryWrapper);
        if (count > 0) {
            throw new CustomException("Combo is still on sale, cannot delete");
        }
        this.removeBatchByIds(ids);

        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);
    }

    @Override
    public void changeStatus(Integer status, List<Long> ids) {
        List<Setmeal> list = ids.stream().map(id -> {
            Setmeal setmeal = new Setmeal();
            setmeal.setStatus(status);
            setmeal.setId(id);
            return setmeal;
        }).collect(Collectors.toList());
        this.updateBatchById(list);
    }

    @Override
    public List<Setmeal> getList(Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Setmeal::getCategoryId, setmeal.getCategoryId())
                .eq(Setmeal::getStatus, setmeal.getStatus());
        return this.list(lambdaQueryWrapper);
    }

}
