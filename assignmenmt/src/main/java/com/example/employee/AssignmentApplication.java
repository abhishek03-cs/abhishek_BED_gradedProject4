package com.example.employee;

import com.example.employee.entity.Role;
import com.example.employee.entity.User;
import com.example.employee.repository.RoleRepository;
import com.example.employee.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AssignmentApplication  {



	@Bean
	ModelMapper getMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
