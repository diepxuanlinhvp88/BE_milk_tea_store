package com.example.demo.mapper;

import com.example.demo.dto.respone.OrderResponse;
import com.example.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper class để chuyển đổi giữa Order entity và OrderResponse DTO.
 * Sử dụng để chuẩn bị dữ liệu trước khi trả về cho client.
 */
@Component
public class OrderMapper {
    
    /**
     * Mapper để chuyển đổi các OrderItem trong đơn hàng
     */
    @Autowired
    private OrderItemMapper orderItemMapper;
    
    /**
     * Chuyển đổi từ Order entity sang OrderResponse DTO
     * @param order Entity đơn hàng cần chuyển đổi
     * @return OrderResponse chứa thông tin đơn hàng đã được định dạng
     */
    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
            order.getId(),
            order.getUser().getName(),
            order.getCreatedAt(),
            order.getStatus(),
            order.getTotalPrice(),
            order.getOrderItems().stream()
                .map(orderItemMapper::toOrderItemResponse)
                .collect(Collectors.toList())
        );
    }
} 