package com.periodico.mundotech.service.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.periodico.mundotech.mapper.UserMapper;
import com.periodico.mundotech.repository.UserRepository;
import com.periodico.mundotech.service.RoleService;

@ExtendWith(MockitoExtension.class)
 class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("deleteUser: elimina el usuario cuando el id coincide y existe")
    void deleteUser_shouldDeleteUser_whenIdMatchesAndUserExists() {
        
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        
        userService.deleteUser(userId, userId);

        
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    @DisplayName("deleteUser: lanza excepción cuando intenta eliminar otro usuario")
    void deleteUser_shouldThrowException_whenIdDoesNotMatchRequestingUser() {
        
        Long userId = 1L;
        Long requestingUserId = 2L;

        
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.deleteUser(userId, requestingUserId));

        assertEquals("Solo puedes eliminar tu propio usuario", exception.getMessage());
        verify(userRepository, never()).deleteById(userId);
    }

    @Test
    @DisplayName("deleteUser: lanza excepción cuando el usuario no existe")
    void deleteUser_shouldThrowException_whenUserDoesNotExist() {
        
        Long userId = 99L;
        when(userRepository.existsById(userId)).thenReturn(false);

        
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.deleteUser(userId, userId));

        assertEquals("Usuario no encontrado con id: " + userId, exception.getMessage());
        verify(userRepository, never()).deleteById(userId);
    }
}
