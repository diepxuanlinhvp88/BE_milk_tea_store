package com.example.demo.service;

import com.example.demo.dto.request.ToppingRequest;
import com.example.demo.dto.respone.ApiResponse;
import com.example.demo.dto.respone.ToppingResponse;
import com.example.demo.entity.Topping;
import com.example.demo.repository.ToppingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ToppingServiceImpl implements ToppingService {
    private ToppingRepository toppingRepository;
    @Override
    public ToppingResponse createTopping(ToppingRequest toppingRequest) {
        Topping topping = new Topping();
        topping.setName(toppingRequest.name());
        topping.setPrice(toppingRequest.price());
        toppingRepository.save(topping);


        return new ToppingResponse(topping.getId(),topping.getName(),topping.getPrice());
    }

    @Override
    public ToppingResponse updateTopping(Long id, ToppingRequest toppingRequest) {
        Topping topping = toppingRepository.findById(id).orElseThrow(() -> new RuntimeException("Topping not found"));
        if(toppingRequest.name() != null){
            topping.setName(toppingRequest.name());
        }
        if(toppingRequest.price() != 0.0){
            topping.setPrice(toppingRequest.price());
        }
        toppingRepository.save(topping);
        return new ToppingResponse(topping.getId(),topping.getName(),topping.getPrice());
    }

    @Override
    public void deleteTopping(Long id) {
        toppingRepository.deleteById(id);

    }

    /**
     * get list of all topping
     * @return list of all topping
     */

    @Override
    public List<ToppingResponse> getAllToppings() {

        List<ToppingResponse> toppingResponses = new ArrayList<>();
        toppingRepository.findAll().forEach(topping -> {
            toppingResponses.add(new ToppingResponse(topping.getId(), topping.getName(), topping.getPrice()));

        });
        return toppingResponses;
    }

    @Override
    public ToppingResponse getTopping(Long id) {
        Topping topping = toppingRepository.findById(id).orElseThrow(() -> new RuntimeException("Topping not found"));
        return new ToppingResponse(topping.getId(), topping.getName(), topping.getPrice());
    }
}
