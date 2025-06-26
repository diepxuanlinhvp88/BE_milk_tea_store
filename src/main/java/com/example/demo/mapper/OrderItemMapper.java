package com.example.demo.mapper;

import com.example.demo.dto.respone.OrderItemResponse;
import com.example.demo.dto.respone.ToppingResponse;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Topping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Chuyển đổi giua DTO và entity của orderItem.
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemMapper {
    @Autowired
    ToppingMapper toppingMapper;

    /**
     * Chuyen tư entity sang DTO.
     * @param orderItem entity
     * @return DTO đã được chuẩn hóa
     */
    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
            orderItem.getId(),
            orderItem.getProduct().getId(),
            orderItem.getProduct().getName(),
            orderItem.getQuantity(),
            orderItem.getPrice(),
            orderItem.getToppings().stream()
                .map(toppingMapper::toToppingResponse)
                .collect(Collectors.toList())
        );
    }


}
