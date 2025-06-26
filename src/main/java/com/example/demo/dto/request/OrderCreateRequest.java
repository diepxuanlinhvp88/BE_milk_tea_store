package com.example.demo.dto.request;

import java.util.List;

/**
 * DTO để tạo mới một đơn hàng.
 * @param customerId ID của khách hàng đặt đơn
 * @param items Danh sách các món trong đơn hàng
 */
public record OrderCreateRequest(
    Long customerId,
    List<OrderItemRequest> items
) {}
