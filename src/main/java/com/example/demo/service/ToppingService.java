package com.example.demo.service;

import com.example.demo.dto.request.ToppingRequest;
import com.example.demo.dto.respone.ApiResponse;
import com.example.demo.dto.respone.ToppingResponse;

import java.util.List;

public interface ToppingService {
    ToppingResponse createTopping(ToppingRequest toppingRequest);
    ToppingResponse updateTopping(Long id, ToppingRequest toppingRequest);
    void deleteTopping(Long id);
    List<ToppingResponse> getAllToppings();
    ToppingResponse getTopping(Long id);

}
