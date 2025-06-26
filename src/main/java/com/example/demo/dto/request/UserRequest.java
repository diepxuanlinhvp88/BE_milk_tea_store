package com.example.demo.dto.request;

/**
 * DTO của khách hàng.
 * @param name tên
 * @param phone số điện thoại
 * @param points điểm tích lũy
 */
public record UserRequest(String name, String phone, int points) {
}
