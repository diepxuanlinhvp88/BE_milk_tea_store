package com.example.demo.dto.respone;

import java.util.List;

public record OrderItemResponse(
        Long id,
        Long productId,
        String productName,
        Integer quantity,
        Double price,
        List<ToppingResponse> toppings
) {
}
