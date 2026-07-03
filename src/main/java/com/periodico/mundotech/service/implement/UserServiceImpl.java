package com.periodico.mundotech.service.implement;

import java.util.List;
import java.util.Set;

import com.periodico.mundotech.entity.Role;

import org.springframework.stereotype.Service;

import com.periodico.mundotech.entity.User;
import com.periodico.mundotech.repository.UserRepository;
import com.periodico.mundotech.service.RoleService;
import com.periodico.mundotech.service.UserService;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService=roleService;
    }

    @Override
    public User createUser(User user, List<Integer> rolesIds) {
        Set<Role> roles=roleService.getAllRoles(rolesIds);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    // @Override
    // public List<User> findAll() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    // }

    // @Override
    // public User findById(Long id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'findById'");
    // }

    // @Override
    // public void deleteById(Long id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    // }

}
