package com.periodico.mundotech.mapper;

import org.springframework.stereotype.Component;

import com.periodico.mundotech.dto.request.RoleRequestDTO;
import com.periodico.mundotech.dto.response.RoleResponseDTO;
import com.periodico.mundotech.entity.Role;

@Component
public class RoleMapper {
    public RoleResponseDTO toResponseDTO(Role role){
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
    }

    public Role toEntity(RoleRequestDTO dto){
        Role role = new Role();
        role.setName(dto.getName());

        return role;
    }
}
