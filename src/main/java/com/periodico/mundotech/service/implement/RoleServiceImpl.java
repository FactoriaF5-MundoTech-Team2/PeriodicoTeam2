package com.periodico.mundotech.service.implement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.periodico.mundotech.entity.Role;
import com.periodico.mundotech.repository.RoleRepository;
import com.periodico.mundotech.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }
    @Override
    public Role creatRole(Role role) {
        return roleRepository.save(role);
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
