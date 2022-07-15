package com.example.domasgka_2_13.controller;


import com.example.domasgka_2_13.model.Employee;
import com.example.domasgka_2_13.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee employeeWithMaxSalaryFromDepartment(@RequestParam int departmentId) {
        return departmentService.employeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee employeeWithMinSalaryFromDepartment(@RequestParam int departmentId) {
        return departmentService.employeeWithMinSalary(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> employeesFromDepartment (@RequestParam int departmentId) {
        return departmentService.employeesFromDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> employeesGroupedByDepartment() {
        return departmentService.employeesGroupedByDepartment();
    }
}
