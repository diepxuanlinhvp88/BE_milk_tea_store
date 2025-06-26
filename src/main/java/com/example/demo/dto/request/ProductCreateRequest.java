package com.example.demo.dto.request;

/**
 * DTO để tạo 1 sản phẩm mới.
 * @param name tên sản phẩm
 * @param description mô tả về sản phầm
 * @param price giá sản phầm
 */
public record ProductCreateRequest(String name, String description, double price) {


}
