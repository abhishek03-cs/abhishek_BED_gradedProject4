package com.example.employee.controller;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.service.EmployeeService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addUser(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.addEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public List<EmployeeDto> listAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/employees/{firstName}")
    public List<EmployeeDto> getEmployeesByFirstName(@PathVariable String firstName){
        return employeeService.getEmployeesByFirstName(firstName);
    }

    @GetMapping
    public List<EmployeeDto> getEmployeesSortedByFirstName(@Param("order") String order){
        return employeeService.getAllEmployeeSortedByFirstName(order);
    }


}
