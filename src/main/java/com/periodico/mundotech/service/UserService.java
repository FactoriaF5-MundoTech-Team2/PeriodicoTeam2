package com.periodico.mundotech.service;
import java.util.List;

import com.periodico.mundotech.entity.User;

public interface UserService {
    User createUser(User user, List<Integer> rolesIds);
    //List<User> findAll();
    // User findById(Long id);
    // void deleteById(Long id);
}
