package com.shaoqin.ez_take_out.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shaoqin.ez_take_out.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: EmployeeMapper
 * Package: com.shaoqin.ez_take_out.mapper
 * Description:
 * Author Shaoqin
 * Create 6/23/23 11:16 PM
 * Version 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
