package com.example.employee.service.service_implementations;

import com.example.employee.custom_exception.NoRecordFoundException;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.UserDto;
import com.example.employee.entity.Employee;
import com.example.employee.entity.User;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;

    @Autowired
   public EmployeeServiceImpl(EmployeeRepository employeeRepository,ModelMapper mapper){
        this.employeeRepository=employeeRepository;
        this.mapper=mapper;
    }
    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
       Employee employee= employeeRepository.findById(id).orElseThrow(()->new NoRecordFoundException(String.format("no employee found with id -> %s",id)));
       return mapToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee= employeeRepository.findById(employeeDto.getId()).orElseThrow(()->new NoRecordFoundException(String.format("no employee found with id -> %s. Please add before trying to update",employeeDto.getId())));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        return mapToDto(employeeRepository.save(employee));
    }

    @Override
    public String deleteEmployeeById(Long id) {
        Employee employee= employeeRepository.findById(id).orElseThrow(()->new NoRecordFoundException(String.format("no employee found with id -> %s, so aborting delete",id)));
        employeeRepository.delete(employee);
        return String.format("employee with id %s deleted successfully",id);
    }

    @Override
    public List<EmployeeDto> getEmployeesByFirstName(String firstName) {
        return employeeRepository.findEmployeesByFirstName(firstName).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getAllEmployeeSortedByFirstName(String sort) {
        Sort orders = sort.equals("ASC")?Sort.by(Sort.Direction.ASC,"firstName"):Sort.by(Sort.Direction.DESC,"firstName");
            return employeeRepository.findAll(orders).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        return mapToDto(employeeRepository.save(employee));
    }

    private Employee mapToEntity(EmployeeDto employeeDto){
        return mapper.map(employeeDto,Employee.class);
    }

    private EmployeeDto mapToDto(Employee employee){
        return mapper.map(employee, EmployeeDto.class);
    }
}
