package com.periodico.mundotech.service.implement;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.periodico.mundotech.dto.request.UserRequestDTO;
import com.periodico.mundotech.dto.response.UserResponseDTO;
import com.periodico.mundotech.entity.Role;
import com.periodico.mundotech.entity.User;
import com.periodico.mundotech.mapper.UserMapper;
import com.periodico.mundotech.repository.UserRepository;
import com.periodico.mundotech.service.RoleService;
import com.periodico.mundotech.service.UserService;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleService=roleService;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto, List<Integer> rolesIds) {
        Set<Role> roles = roleService.getAllRoles(rolesIds);
        if (roles.isEmpty()) {
            throw new RuntimeException("debe asignar al menos un rol al usuario");
        }
        User user = userMapper.toEntity(dto);
        user.setRoles(roles);
        User saved = userRepository.save(user);
        return userMapper.toResponseDTO(saved);
    }
}
