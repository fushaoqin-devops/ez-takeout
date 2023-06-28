package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.entity.Setmeal;
import com.shaoqin.ez_take_out.mapper.SetmealMapper;
import com.shaoqin.ez_take_out.service.SetmealService;
import org.springframework.stereotype.Service;

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

    @Override
    public Long getSetmealCountByCategoryId(Long id) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        return this.count(setmealLambdaQueryWrapper);
    }

}
