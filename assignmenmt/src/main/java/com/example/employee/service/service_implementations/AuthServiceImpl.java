package com.example.employee.service.service_implementations;

import com.example.employee.dto.UserDto;
import com.example.employee.entity.Role;
import com.example.employee.entity.User;
import com.example.employee.repository.RoleRepository;
import com.example.employee.repository.UserRepository;
import com.example.employee.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private ModelMapper mapper;
   // private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper mapper,RoleRepository roleRepository){
        this.userRepository= userRepository;
        this.mapper=mapper;
       // this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;

    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        if(userRepository.existsByUserName(userDto.getUserName()))
            throw new RuntimeException("user already exists in database");
       User user = new User();
       user.setUserName(userDto.getUserName());
       user.setPassword(userDto.getPassword());
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRoleName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        return mapToDto(userRepository.save(user));

    }
    private UserDto mapToDto(User user){
        return mapper.map(user, UserDto.class);
    }
}
