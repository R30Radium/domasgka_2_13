package com.example.domasgka_2_13;

import com.example.domasgka_2_13.exception.*;
import com.example.domasgka_2_13.model.Employee;
import com.example.domasgka_2_13.service.EmployeeService;
import com.example.domasgka_2_13.service.ValidatorService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());


    @ParameterizedTest
    @MethodSource("paramsForAdd")
    public void addNegativeTest1(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(name, surname, department, salary));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest2(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        List<Employee> employees = generateEmployees(10);
        employees.forEach(employee -> assertThat(employeeService.add(employee.getName(),
                employee.getSurname(), employee.getDepartment(), employee.getSalary()))
                .isEqualTo(employee));

        assertThatExceptionOfType(EmployeeStorageFullException.class)
                .isThrownBy(()-> employeeService.add(name, surname, department, salary));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest3() {
        assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.add("kuda","tuda",
                        1,12_345));
        assertThatExceptionOfType(IncorrectSurnameException.class)
                .isThrownBy(() -> employeeService.add("kuda","tuda",
                        1,12_345));
        assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.add("kud$%a","tu2da",
                        1,12_345));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removeNegativeTest(String name,
                                   String surname,
                                   int department,
                                   double salary) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("kto-to", "chto-to"));

        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("kto-to", "chto-to"));

    }

    @ParameterizedTest
    @MethodSource("params")
    public void removePositiveTest(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);

        assertThat(employeeService.remove(name, surname)).isEqualTo(expected);
        assertThat(employeeService.getAll()).isEmpty();
    }


    @ParameterizedTest
    @MethodSource("params")
    public void findNegativeTest(String name,
                                 String surname,
                                 int department,
                                 double salary) {
       assertThatExceptionOfType(EmployeeNotFoundException.class)
               .isThrownBy(()-> employeeService.find("kto-to","chto-to"));

       Employee expected = new Employee(name, surname, department, salary);
       assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);
       assertThatExceptionOfType(EmployeeNotFoundException.class)
               .isThrownBy(()->employeeService.find("kto-to", "chto-to"));
    }

    @ParameterizedTest
    @MethodSource
    public void findPositiveTest(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);

        assertThat(employeeService.find(name, surname)).isEqualTo(expected);
        assertThat(employeeService.getAll()).hasSize(1);
    }

    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1,i ->i+1).limit(size)
                .map(i-> new Employee("Name"+(char) ((int) 'a' + i), "Surname" + (char) ((int) 'a' + i),
                        i, 10_000 + i)).collect(Collectors.toList());
    }

    public static Stream<Arguments> paramsForAdd() {
        return Stream.of(
                Arguments.of("Kliment", "Ivanov", 1, 55_000),
                Arguments.of("Amer","Al-Barkavi", 1,78_000),
                Arguments.of("Anisia","Golovanova", 2, 94_000)
        );
    }

    public static Stream<Arguments> paramsForRemove() {
        return Stream.of(
                Arguments.of("Kliment", "Ivanov", 1, 55_000),
                Arguments.of("Amer","Al-Barkavi", 1,78_000),
                Arguments.of("Anisia","Golovanova", 2, 94_000)
        );
    }
}