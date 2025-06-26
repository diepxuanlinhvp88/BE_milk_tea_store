package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Đại diện cho phần topping thêm vào với sản phẩm tạo thành 1 món hàng trong 1 đơn hàng, VD: tran chau...
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topping {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double price;

    /**
     * Danh sách các OrderItem sử dụng topping này
     * Quan hệ ngược lại với OrderItem, mappedBy chỉ định field bên OrderItem
     */
    @ManyToMany(mappedBy = "toppings")
    private List<OrderItem> orderItems;
}
