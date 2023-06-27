package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.entity.Category;
import com.shaoqin.ez_take_out.mapper.CategoryMapper;
import com.shaoqin.ez_take_out.service.CategoryService;
import org.springframework.stereotype.Service;

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

}
