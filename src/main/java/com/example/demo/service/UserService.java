package com.example.demo.service;

import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.entity.User;

public interface UserService {
    User createCustomer(UserCreateRequest userCreateRequest);

}
