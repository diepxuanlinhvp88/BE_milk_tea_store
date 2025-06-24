package com.example.demo.controller;

import com.example.demo.dto.request.ToppingRequest;
import com.example.demo.dto.respone.ToppingResponse;
import com.example.demo.entity.Topping;
import com.example.demo.service.ToppingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/toppings")
public class ToppingController {
    private final ToppingService toppingService;

    @PostMapping()
    public ToppingResponse addTopping(@RequestBody ToppingRequest topping) {
        return toppingService.createTopping(topping);
    }

    @GetMapping()
    public List<ToppingResponse> getAllToppings() {
        return toppingService.getAllToppings();
    }

    @GetMapping("/{id}")
    public ToppingResponse getTopping(@PathVariable Long id) {
        return toppingService.getTopping(id);
    }

    @PutMapping("/{id}")
    public ToppingResponse updateTopping(@PathVariable Long id, @RequestBody ToppingRequest topping) {
        return toppingService.updateTopping(id, topping);
    }

    @DeleteMapping("/{id}")
    public void deleteTopping(@PathVariable Long id) {
        toppingService.deleteTopping(id);
    }
}
