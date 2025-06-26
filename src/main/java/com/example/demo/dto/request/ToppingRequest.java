package com.example.demo.dto.request;

/**
 * DTO gửi yêu cầu với topping, thêm, sửa topping.
 * @param id id của topping
 * @param name tên
 * @param price giá
 */
public record ToppingRequest(Long id, String name, double price) {
}
