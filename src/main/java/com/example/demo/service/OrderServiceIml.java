package com.example.demo.service;

import com.example.demo.dto.request.OrderCreateRequest;
import com.example.demo.dto.request.OrderItemRequest;
import com.example.demo.dto.respone.OrderResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceIml implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;

    @Override
    public List<OrderItem> getOrderItems() {
        return List.of();
    }

    @Override
    @Transactional
    public OrderResponse createOrder(OrderCreateRequest orderCreateRequest) {
        User user = userRepository.findById(orderCreateRequest.customerId()).orElseThrow();
        Order order = new Order();
        order.setUser(user);
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0;

        for (OrderItemRequest orderItemRequest : orderCreateRequest.items()) {
            OrderItem orderItem = orderItemService.createOrderItem(orderItemRequest);
            orderItems.add(orderItem);
            totalPrice += orderItem.getPrice();
        }

        return new OrderResponse(order.getId(), user.getName(),order.getCreatedAt(),order.getStatus(),order.getTotalPrice(),orderItems);





    }
}
