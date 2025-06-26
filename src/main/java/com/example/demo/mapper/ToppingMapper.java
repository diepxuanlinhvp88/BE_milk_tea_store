package com.example.demo.mapper;

import com.example.demo.dto.respone.ToppingResponse;
import com.example.demo.entity.Topping;
import org.springframework.stereotype.Component;

/**
 * Chuyển đổi giũa entity và dto của topping.
 */
@Component
public class ToppingMapper {
    public ToppingResponse toToppingResponse(Topping topping) {
        return new ToppingResponse(
                topping.getId(),
                topping.getName(),
                topping.getPrice()
        );
    }
}
