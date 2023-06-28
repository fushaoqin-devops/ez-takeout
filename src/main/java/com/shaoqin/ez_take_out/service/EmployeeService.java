package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Employee;

/**
 * ClassName: EmployeeService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/23/23 11:18 PM
 * Version 1.0
 */
public interface EmployeeService extends IService<Employee> {

    public Employee getEmployeeByUsername(Employee employee);

    public Page<Employee> getEmployeePage(PageDto pageDto);

    public R<Employee> validateEmployeeLogin(Employee employee);

}
