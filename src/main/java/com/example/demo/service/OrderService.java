package com.example.demo.service;

import com.example.demo.dto.request.OrderCreateRequest;
import com.example.demo.dto.respone.OrderResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

import java.util.List;

public interface OrderService {
    List<OrderItem> getOrderItems();
    OrderResponse createOrder(OrderCreateRequest orderCreateRequest);
}
