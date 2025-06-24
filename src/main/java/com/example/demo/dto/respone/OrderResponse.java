package com.example.demo.dto.respone;

import com.example.demo.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
    Long id,
    String customerName,
    LocalDateTime createdAt,
    String status,
    Double totalPrice,
    List<OrderItem> items
) {} 