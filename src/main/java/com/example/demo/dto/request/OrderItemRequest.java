package com.example.demo.dto.request;

import java.util.List;

public record OrderItemRequest(
    Long productId,
    Integer quantity,
    List<Long> toppingIds
) {}
