package com.example.demo.service;

import com.example.demo.dto.request.OrderItemRequest;
import com.example.demo.dto.respone.OrderItemResponse;
import com.example.demo.entity.OrderItem;

/**
 * Interface định nghĩa các phương thức xử lý nghiệp vụ liên quan đến chi tiết đơn hàng.
 * Cung cấp các thao tác để quản lý từng món trong đơn hàng.
 */
public interface OrderItemService {

    /**
     * Thêm một món vào đơn hàng
     * @param request Thông tin món cần thêm vào đơn hàng
     * @return OrderItem entity đã được tạo
     */
    OrderItem addOrderItem(OrderItemRequest request);

    /**
     * Lấy thông tin của một món trong đơn hàng theo ID
     * @param id ID của món cần tìm
     * @return OrderItemResponse chứa thông tin chi tiết của món
     */
    OrderItemResponse getOrderItemById(Long id);

    /**
     * Xóa một món khỏi đơn hàng
     * @param id ID của món cần xóa
     */
    void deleteOrderItemById(Long id);
}
