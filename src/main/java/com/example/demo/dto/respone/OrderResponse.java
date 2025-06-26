package com.example.demo.dto.respone;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
    Long id,
    String customerName,
    LocalDateTime createdAt,
    String status,
    Double totalPrice,
    List<OrderItemResponse> items
) {} 