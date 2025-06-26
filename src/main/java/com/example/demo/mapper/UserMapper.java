package com.example.demo.mapper;

import com.example.demo.dto.respone.UserResponse;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

/**
 * Chuyển đổi liên quan đến user.
 */
@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse(
                user.getId(), user.getName(), user.getPoints()
        );
        return userResponse;
    }
}
