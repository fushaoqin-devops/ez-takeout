package com.shaoqin.ez_take_out.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.dto.SetmealDto;
import com.shaoqin.ez_take_out.entity.Setmeal;
import com.shaoqin.ez_take_out.service.SetmealDishService;
import com.shaoqin.ez_take_out.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping
    public R<String> delete(@RequestParam("ids") List<Long>ids) {
        setmealService.removeWithDish(ids);
        return R.success("Combo deleted");
    }

    @PostMapping("status/{status}")
    public R<String> changeStatus(@PathVariable("status") Integer status, @RequestParam("ids") List<Long> ids) {
        setmealService.changeStatus(status, ids);
        return R.success("Combo status updated");
    }

    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        List<Setmeal> list = setmealService.getList(setmeal);
        return R.success(list);
    }

}
