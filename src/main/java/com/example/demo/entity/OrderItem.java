package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity đại diện cho một món trong đơn hàng.
 * Chứa thông tin về số lượng, giá và các topping đi kèm của một sản phẩm trong đơn hàng.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    /**
     * ID của món trong đơn hàng, tự động tăng
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Số lượng sản phẩm đặt
     */
    private Integer quantity;

    /**
     * Giá của món (đã bao gồm giá topping nếu có)
     */
    private Double price;

    /**
     * Đơn hàng chứa món này
     * Quan hệ nhiều-một với bảng Order
     */
    @ManyToOne
    private Order order;

    /**
     * Sản phẩm được đặt
     * Quan hệ nhiều-một với bảng Product
     */
    @ManyToOne
    private Product product;

    /**
     * Danh sách các topping được chọn cho món này
     * Quan hệ nhiều-nhiều với bảng Topping, sử dụng bảng trung gian orderitem_topping
     * Thêm orphanRemoval để tự động xóa các liên kết khi topping bị xóa
     */
    @ManyToMany
    @JoinTable(
            name = "orderitem_topping",
            joinColumns = @JoinColumn(name = "order_item_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id")
    )
    private List<Topping> toppings;
}
