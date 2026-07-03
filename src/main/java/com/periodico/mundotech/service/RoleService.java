package com.periodico.mundotech.service;
import java.util.List;
import java.util.Set;
import com.periodico.mundotech.entity.Role;

public interface RoleService {
    public Role creatRole(Role role);
    public Set<Role> getAllRoles(List<Integer> rolesIds);

}
