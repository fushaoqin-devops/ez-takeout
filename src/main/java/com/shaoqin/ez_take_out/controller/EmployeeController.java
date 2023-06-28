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

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        R<Employee> result = employeeService.validateEmployeeLogin(employee);
        if (result.getCode() == 1)  request.getSession().setAttribute("employee", result.getData().getId());
        return result;
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("Logout success");
    }

    @PostMapping()
    public R<String> save(@RequestBody Employee employee) {
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.save(employee);

        return R.success("Employee added");
    }

    @PostMapping("/check-username")
    public R<String> checkUsername(@RequestBody Employee employee) {
        Employee emp = employeeService.getEmployeeByUsername(employee);
        return emp == null ? R.success("Username can be used") : R.error("Username already exists");
    }

    @GetMapping("/page")
    public R<Page<Employee>> page(PageDto pageDto) {
        Page<Employee> pageInfo = employeeService.getEmployeePage(pageDto);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        employeeService.updateById(employee);
        return R.success("Employee updated");
    }

    @GetMapping("/{id}")
    public R<Employee> get(@PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);
        return employee != null ? R.success(employee) : R.error("No employee record found");
    }

}
