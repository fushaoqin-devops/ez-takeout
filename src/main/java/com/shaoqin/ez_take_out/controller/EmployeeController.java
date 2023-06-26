package com.shaoqin.ez_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.Employee;
import com.shaoqin.ez_take_out.service.EmployeeService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * ClassName: EmployeeController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/23/23 11:25 PM
 * Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Handle login
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        // Encrypt md5
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // Get user by username
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // Check if user exists
        if (emp == null) return R.error("Login failed");

        // Validate password
        if (!emp.getPassword().equals(password)) return R.error("Login failed");

        // Check user status
        if (emp.getStatus() == 0) return R.error("Account disabled");

        // Store user info in session
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * Handle logout
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        // Remove user from session
        request.getSession().removeAttribute("employee");
        return R.success("Logout success");
    }

    /**
     * Add new employee
     * @param employee
     * @return
     */
    @PostMapping()
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("Employee added");
    }

    @PostMapping("/check-username")
    public R<String> checkUsername(@RequestBody Employee employee) {
        // Get user by username
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        return emp == null ? R.success("Username can be used") : R.error("Username already exists");
    }

    @GetMapping("/page")
    public R<Page> page(PageDto pageDto) {
        Page pageInfo = new Page(pageDto.getPage(), pageDto.getPageSize());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotEmpty(pageDto.getName()), Employee::getName, pageDto.getName());
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);
        return R.success("Employee updated");
    }

    @GetMapping("/{id}")
    public R<Employee> get(@PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);

        return employee != null ? R.success(employee) : R.error("No employee record found");
    }

}
