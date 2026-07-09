package com.periodico.mundotech.service.implement;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.periodico.mundotech.dto.request.RoleRequestDTO;
import com.periodico.mundotech.dto.response.RoleResponseDTO;
import com.periodico.mundotech.entity.Role;
import com.periodico.mundotech.mapper.RoleMapper;
import com.periodico.mundotech.repository.RoleRepository;
import com.periodico.mundotech.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO dto) {
        Role role = roleMapper.toEntity(dto);
        Role saved = roleRepository.save(role);
        return roleMapper.toResponseDTO(saved);
    }

    @Override
    public Set<Role> getAllRoles(List<Integer> rolesId) {
        Set<Role> roles = roleRepository.findAllById(rolesId).stream().collect(Collectors.toSet());
        if (roles.size() != rolesId.size()) {
            throw new RuntimeException("Uno o mas roles no existen");
        }
        return roles;
    }
}