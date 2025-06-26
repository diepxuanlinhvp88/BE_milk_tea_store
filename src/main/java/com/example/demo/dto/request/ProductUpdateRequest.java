package com.example.demo.dto.request;

/**
 * DTO cập nhật san phẩm.
 * @param name tên sản phầm
 * @param description mô tả sản phầm
 * @param price giá sản phẩm
 */
public record ProductUpdateRequest(String name, String description, double price) {
}
