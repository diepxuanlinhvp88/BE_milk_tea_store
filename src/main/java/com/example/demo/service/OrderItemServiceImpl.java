package com.example.demo.service;

import com.example.demo.dto.request.OrderItemRequest;
import com.example.demo.dto.respone.OrderItemResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.Topping;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ToppingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ToppingRepository toppingRepository;

    @Override
    @Transactional
    public OrderItem createOrderItem(OrderItemRequest request) {
        Product product = productRepository.findById(request.productId()).orElse(null);
        List<Topping> toppings = toppingRepository.findAllById(request.toppingIds());

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setToppings(toppings);
        orderItem.setQuantity(request.quantity());
        double price = calculateTotalPrice(request.quantity(),toppings,product);
        orderItem.setPrice(price);

        orderItemRepository.save(orderItem);
        return orderItem;
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        return null;
    }

    @Override
    public OrderItemResponse updateOrderItem(Long id, OrderItemRequest request) {
        return null;
    }

    @Override
    public void deleteOrderItemById(Long id) {

    }

    @Override
    public OrderItemResponse createOrderItemResponse(OrderItem orderItem) {
        return null;
    }
    double calculateTotalPrice(int quantity, List<Topping> toppings, Product product ) {
        double totalPrice = 0;
        totalPrice = product.getPrice() * quantity;
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice() * quantity;
        }
        return totalPrice;

    }
}
