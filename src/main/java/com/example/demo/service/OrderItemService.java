package com.example.demo.service;

import com.example.demo.dto.request.OrderItemRequest;
import com.example.demo.dto.respone.OrderItemResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItemRequest request);
    OrderItemResponse getOrderItemById(Long id);
    OrderItemResponse updateOrderItem(Long id, OrderItemRequest request);
    void deleteOrderItemById(Long id);
    OrderItemResponse createOrderItemResponse(OrderItem orderItem);
}
