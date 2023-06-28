package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Employee;
import com.shaoqin.ez_take_out.mapper.EmployeeMapper;
import com.shaoqin.ez_take_out.service.EmployeeService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * ClassName: EmployeeServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/23/23 11:19 PM
 * Version 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public Employee getEmployeeByUsername(Employee employee) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        return this.getOne(queryWrapper);
    }

    @Override
    public Page<Employee> getEmployeePage(PageDto pageDto) {
        Page<Employee> pageInfo = new Page<>(pageDto.getPage(), pageDto.getPageSize());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(pageDto.getName()), Employee::getName, pageDto.getName());
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        this.page(pageInfo, queryWrapper);

        return pageInfo;
    }

    @Override
    public R<Employee> validateEmployeeLogin(Employee employee) {
        // Encrypt md5
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        Employee emp = this.getEmployeeByUsername(employee);

        // Check if user exists
        if (emp == null) return R.error("Login failed");

        // Validate password
        if (!emp.getPassword().equals(password)) return R.error("Login failed");

        // Check user status
        if (emp.getStatus() == 0) return R.error("Account disabled");

        return R.success(emp);
    }

}
