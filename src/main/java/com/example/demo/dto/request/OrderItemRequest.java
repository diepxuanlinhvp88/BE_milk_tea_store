package com.example.demo.dto.request;

import java.util.List;

/**
 * DTO chứa thông tin chi tiết của một món trong đơn hàng.
 * @param productId ID của sản phẩm được đặt
 * @param quantity Số lượng sản phẩm
 * @param toppingIds Danh sách ID của các topping được chọn cho sản phẩm
 */
public record OrderItemRequest(
    Long productId,
    Integer quantity,
    List<Long> toppingIds
) {}
