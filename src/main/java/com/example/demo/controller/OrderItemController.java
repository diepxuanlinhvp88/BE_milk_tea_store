//package com.example.demo.controller;
//
//import com.example.demo.dto.request.OrderItemRequest;
//import com.example.demo.dto.respone.OrderItemResponse;
//import com.example.demo.service.OrderItemService;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/v1/order-item")
//@AllArgsConstructor
//public class OrderItemController {
//
//    private final OrderItemService orderItemService;
//    @PostMapping()
//    public OrderItemResponse createOrderItem(@RequestBody OrderItemRequest request){
//        return orderItemService.createOrderItem(request);
//    }
//
//}
