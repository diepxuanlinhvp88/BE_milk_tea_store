package com.example.demo.service;

import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createCustomer(UserCreateRequest userCreateRequest) {
        User newcustomer = new User();
        newcustomer.setName(userCreateRequest.name());
        newcustomer.setPhone(userCreateRequest.phone());
        newcustomer.setPoints(userCreateRequest.points());
        userRepository.save(newcustomer);

        return newcustomer;
    }
}
