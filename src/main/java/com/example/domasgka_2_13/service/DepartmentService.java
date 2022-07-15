package com.example.domasgka_2_13.service;

import com.example.domasgka_2_13.exception.EmployeeNotFoundException;
import com.example.domasgka_2_13.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee employeeWithMaxSalary(int departmentID) {
        return employeeService.getAll().stream()
                .filter(employee ->employee.getDepartment() == departmentID)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee employeeWithMinSalary(int departmentID) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentID)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);

    }
    public List<Employee> employeesFromDepartment(int departmentID) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentID)
                .collect(Collectors.toList());
    }

    public Map<Integer , List<Employee>> employeesGroupedByDepartment() {
            return employeeService.getAll().stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
