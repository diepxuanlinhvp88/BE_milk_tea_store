package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.respone.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation của UserService interface.
 * Xử lý các nghiệp vụ liên quan đến người dùng trong hệ thống.
 */
@Service
public class UserServiceImpl implements UserService {
    // Repository để thao tác với dữ liệu người dùng
    @Autowired
    private UserRepository userRepository;

    // Mapper để chuyển đổi giữa User entity và DTO
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse createCustomer(UserRequest userRequest) {
        // Tạo mới đối tượng User
        User user = new User();
        user.setName(userRequest.name());
        user.setPhone(userRequest.phone());
        user.setPoints(userRequest.points());

        // Lưu người dùng vào database
        userRepository.save(user);

        // Chuyển đổi và trả về response
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getUser(Long id) {
        // Tìm người dùng theo ID, throw exception nếu không tìm thấy
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        // Tìm người dùng theo ID
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Cập nhật thông tin người dùng
        user.setName(userRequest.name());
        user.setPhone(userRequest.phone());
        user.setPoints(userRequest.points());

        // Lưu thông tin đã cập nhật
        userRepository.save(user);

        // Chuyển đổi và trả về response
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        // Xóa người dùng theo ID
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        // Lấy danh sách tất cả người dùng
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        // Chuyển đổi từng người dùng sang DTO
        for (User user : users) {
            userResponses.add(userMapper.toUserResponse(user));
        }
        return userResponses;
    }
}
