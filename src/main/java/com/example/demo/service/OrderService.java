package com.example.demo.service;

import com.example.demo.dto.request.OrderCreateRequest;
import com.example.demo.dto.respone.OrderResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

import java.util.List;

/**
 * Interface định nghĩa các phương thức xử lý nghiệp vụ liên quan đến đơn hàng.
 * Cung cấp các thao tác CRUD và các thao tác nghiệp vụ khác cho đơn hàng.
 */
public interface OrderService {

    /**
     * Tạo mới một đơn hàng
     * @param orderCreateRequest Thông tin đơn hàng cần tạo
     * @return OrderResponse chứa thông tin đơn hàng đã được tạo
     */
    OrderResponse createOrder(OrderCreateRequest orderCreateRequest);

    /**
     * Lấy thông tin một đơn hàng theo ID
     * @param orderId ID của đơn hàng cần tìm
     * @return OrderResponse chứa thông tin đơn hàng
     */
    OrderResponse getOrder(Long orderId);

    /**
     * Lấy danh sách đơn hàng của một khách hàng
     * @param userId ID của khách hàng
     * @return Danh sách các đơn hàng của khách hàng
     */
    List<OrderResponse> getOrdersbyUserId(Long userId);

    /**
     * Hủy một đơn hàng
     * @param orderId ID của đơn hàng cần hủy
     */
    void cancelOrder(Long orderId);

    /**
     * Xóa một đơn hàng khỏi hệ thống
     * @param orderId ID của đơn hàng cần xóa
     */
    void deleteOrder(Long orderId);

    /**
     * Lấy danh sách tất cả các đơn hàng trong hệ thống
     * @return Danh sách tất cả các đơn hàng
     */
    List<OrderResponse> getAllOrders();
}
