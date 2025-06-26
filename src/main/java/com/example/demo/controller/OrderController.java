package com.example.demo.controller;

import com.example.demo.dto.request.OrderCreateRequest;
import com.example.demo.dto.respone.OrderItemResponse;
import com.example.demo.dto.respone.OrderResponse;
import com.example.demo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @PostMapping()
    OrderResponse createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        LOG.info("Nhận yêu cầu tạo đơn hàng mới từ khách hàng ID: {}", orderCreateRequest.customerId());
        OrderResponse response = orderService.createOrder(orderCreateRequest);
        LOG.info("Đã tạo thành công đơn hàng ID: {}", response.id());
        return response;
    }

    @GetMapping()
    List<OrderResponse> getOrderItems() {
        LOG.info("Nhận yêu cầu lấy danh sách tất cả đơn hàng");
        List<OrderResponse> orders = orderService.getAllOrders();
        LOG.info("Trả về {} đơn hàng", orders.size());
        return orders;
    }

    @GetMapping("/{id}")
    OrderResponse getOrderById(@PathVariable Long id) {
        LOG.info("Nhận yêu cầu lấy thông tin đơn hàng ID: {}", id);
        OrderResponse order = orderService.getOrder(id);
        LOG.info("Đã tìm thấy đơn hàng ID: {}", id);
        return order;
    }
}
