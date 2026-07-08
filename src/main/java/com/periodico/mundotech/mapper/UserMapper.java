package com.periodico.mundotech.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.periodico.mundotech.dto.request.UserRequestDTO;
import com.periodico.mundotech.dto.response.UserResponseDTO;
import com.periodico.mundotech.entity.Role;
import com.periodico.mundotech.entity.User;

@Component
public class UserMapper {
    public UserResponseDTO toResponseDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream()
        .map(Role::getName)
        .collect(Collectors.toSet()));
        
        return dto;
    }

    public User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }
}
