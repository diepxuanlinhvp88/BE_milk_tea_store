package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.respone.UserResponse;

import java.util.List;

/**
 * Interface định nghĩa các phương thức xử lý nghiệp vụ liên quan đến người dùng.
 * Cung cấp các thao tác CRUD và các thao tác nghiệp vụ khác cho người dùng.
 */
public interface UserService {

    /**
     * Tạo mới một tài khoản khách hàng
     * @param userRequest Thông tin khách hàng cần tạo
     * @return UserResponse chứa thông tin khách hàng đã được tạo
     */
    UserResponse createCustomer(UserRequest userRequest);

    /**
     * Lấy thông tin của một người dùng theo ID
     * @param id ID của người dùng cần tìm
     * @return UserResponse chứa thông tin người dùng
     */
    UserResponse getUser(Long id);

    /**
     * Cập nhật thông tin của một người dùng
     * @param id ID của người dùng cần cập nhật
     * @param userRequest Thông tin cập nhật của người dùng
     * @return UserResponse chứa thông tin người dùng sau khi cập nhật
     */
    UserResponse updateUser(Long id, UserRequest userRequest);

    /**
     * Xóa một người dùng khỏi hệ thống
     * @param id ID của người dùng cần xóa
     */
    void deleteUser(Long id);

    /**
     * Lấy danh sách tất cả người dùng trong hệ thống
     * @return Danh sách các người dùng
     */
    List<UserResponse> getAllUsers();
}
