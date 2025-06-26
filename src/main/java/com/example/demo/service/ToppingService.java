package com.example.demo.service;

import com.example.demo.dto.request.ToppingRequest;
import com.example.demo.dto.respone.ApiResponse;
import com.example.demo.dto.respone.ToppingResponse;

import java.util.List;

/**
 * Interface định nghĩa các phương thức xử lý nghiệp vụ liên quan đến topping.
 * Cung cấp các thao tác CRUD và các thao tác nghiệp vụ khác cho topping.
 */
public interface ToppingService {

    /**
     * Tạo mới một topping
     * @param toppingRequest Thông tin topping cần tạo
     * @return ToppingResponse chứa thông tin topping đã được tạo
     */
    ToppingResponse createTopping(ToppingRequest toppingRequest);

    /**
     * Cập nhật thông tin của một topping
     * @param id ID của topping cần cập nhật
     * @param toppingRequest Thông tin cập nhật của topping
     * @return ToppingResponse chứa thông tin topping sau khi cập nhật
     */
    ToppingResponse updateTopping(Long id, ToppingRequest toppingRequest);

    /**
     * Xóa một topping khỏi hệ thống
     * @param id ID của topping cần xóa
     */
    void deleteTopping(Long id);

    /**
     * Lấy danh sách tất cả các topping trong hệ thống
     * @return Danh sách các topping
     */
    List<ToppingResponse> getAllToppings();

    /**
     * Lấy thông tin của một topping theo ID
     * @param id ID của topping cần tìm
     * @return ToppingResponse chứa thông tin topping
     */
    ToppingResponse getTopping(Long id);
}
