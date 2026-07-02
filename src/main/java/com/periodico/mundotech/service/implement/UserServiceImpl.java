package com.periodico.mundotech.service.implement;

import org.springframework.stereotype.Service;

import com.periodico.mundotech.entity.User;
import com.periodico.mundotech.repository.UserRepository;
import com.periodico.mundotech.service.UserService;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
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
