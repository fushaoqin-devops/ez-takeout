package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.dto.SetmealDto;
import com.shaoqin.ez_take_out.entity.Setmeal;

import java.util.List;

/**
 * ClassName: SetmealService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/27/23 1:13 PM
 * Version 1.0
 */
public interface SetmealService extends IService<Setmeal> {

    public Long getSetmealCountByCategoryId(Long id);

    public void saveWithDish(SetmealDto setmealDto);

    public Page<SetmealDto> getSetmealPage(PageDto pageDto);

    public void removeWithDish(List<Long>ids);

    void changeStatus(Integer status, List<Long> ids);

    List<Setmeal> getList(Setmeal setmeal);

}
