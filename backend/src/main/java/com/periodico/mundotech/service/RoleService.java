package com.periodico.mundotech.service;
import java.util.List;
import java.util.Set;

import com.periodico.mundotech.dto.request.RoleRequestDTO;
import com.periodico.mundotech.dto.response.RoleResponseDTO;
import com.periodico.mundotech.entity.Role;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO dto);
    Set<Role> getAllRoles(List<Integer> rolesIds);

}
