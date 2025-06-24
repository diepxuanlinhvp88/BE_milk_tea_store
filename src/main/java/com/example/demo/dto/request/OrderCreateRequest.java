package com.example.demo.dto.request;

import java.util.List;

public record OrderCreateRequest(
    Long customerId,
    List<OrderItemRequest> items
) {}
