package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.UserDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);

    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    String deleteEmployeeById(Long id);

    List<EmployeeDto> getEmployeesByFirstName(String firstName);

    List<EmployeeDto> getAllEmployeeSortedByFirstName(String sort);

    EmployeeDto addEmployee(EmployeeDto employeeDto);
}
