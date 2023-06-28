package com.shaoqin.ez_take_out.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.dto.SetmealDto;
import com.shaoqin.ez_take_out.service.SetmealDishService;
import com.shaoqin.ez_take_out.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SetmealController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/28/23 2:31 AM
 * Version 1.0
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        setmealService.saveWithDish(setmealDto);
        return R.success("Combo added");
    }

    @GetMapping("/page")
    public  R<Page<SetmealDto>> page(PageDto pageDto) {
        Page<SetmealDto> page = setmealService.getSetmealPage(pageDto);
        return R.success(page);
    }

}
