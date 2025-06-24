package com.example.demo.dto.respone;

import com.example.demo.entity.Topping;
import lombok.Data;

import java.util.List;

public record OrderItemResponse(
        Long id,
        Long productId,
        String productName,
        Integer quantity,
        Double price,
        List<Topping> toppings
) {
}
