package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/26/23 7:57 PM
 * Version 1.0
 */
public interface CategoryService extends IService<Category> {

    public void remove(Long id, Long dishCount, Long setmealCount);

    public List<Category> getCategoryList(Category category);

    public Page<Category> getCategoryPage(PageDto pageDto);

}
