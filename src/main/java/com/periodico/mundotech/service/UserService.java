package com.periodico.mundotech.service;

import java.util.List;

import com.periodico.mundotech.dto.request.LoginRequestDTO;
import com.periodico.mundotech.dto.request.UserRequestDTO;
import com.periodico.mundotech.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO dto, List<Integer> rolesIds);
    UserResponseDTO login(LoginRequestDTO dto);

    void deleteUser(Long id, Long requestingUserId);
}
