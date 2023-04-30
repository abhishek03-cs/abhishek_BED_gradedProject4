package com.example.employee.controller;

import com.example.employee.dto.UserDto;
import com.example.employee.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService service){
        this.authService= service;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto){
        authService.saveUser(userDto);
        return "User added successfully";
    }

}
