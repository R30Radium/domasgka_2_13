package com.example.domasgka_2_13;

import com.example.domasgka_2_13.exception.EmployeeNotFoundException;
import com.example.domasgka_2_13.model.Employee;
import com.example.domasgka_2_13.service.DepartmentService;
import com.example.domasgka_2_13.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach(){
        List<Employee> employees = List.of(
                new Employee("Gavno","Jopa",1,10_000),
                new Employee("Gavno","Jopa",1,10_000),
                new Employee("Gavno","Jopa",1,10_000),
                new Employee("Gavno","Jopa",1,10_000),
                new Employee("Gavno","Jopa",1,10_000),
                new Employee("Gavno","Jopa",1,10_000));
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void employeeWithMaxSalaryPositiveTest(int departmentId, Employee expected) {
        assertThat(departmentService.employeeWithMaxSalary(departmentId)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> departmentService.employeeWithMaxSalary(3));
    }
//1:26:40//
    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void employeeWithSalaryPositiveTest(int departmentId, Employee expected) {
        assertThat(departmentService.employeeWithMaxSalary(departmentId)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()-> departmentService.employeeWithMinSalary(3));
    }

    public static Stream<Arguments> employeeWithMaxSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("gavno","gavno",1,22222)),
                Arguments.of(2, new Employee("gavno","gavno",1,22222)));
    }

    public static Stream<Arguments> employeeWithMinSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("gavno","gavno",1,22222)),
                Arguments.of(2, new Employee("gavno","gavno",1,22222)));
    }
}
