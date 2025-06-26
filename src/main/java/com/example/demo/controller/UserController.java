package com.example.demo.controller;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.respone.OrderResponse;
import com.example.demo.dto.respone.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @PostMapping()
    UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createCustomer(userRequest);
    }
    @GetMapping()
    List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @PutMapping("/{id}")
    UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }
    @GetMapping("/{id}/orders")
    List<OrderResponse> getAllOrders(@PathVariable Long id) {
        return orderService.getOrdersbyUserId(id);
    }
}
