package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity đại diện cho một đơn hàng trong hệ thống.
 * Sử dụng annotation @Table với tên "order" trong dấu ` vì order là từ khóa trong SQL.
 */
@Entity
@Table(name = "`order`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     * ID của đơn hàng, tự động tăng
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Thời gian tạo đơn hàng
     */
    private LocalDateTime createdAt;

    /**
     * Trạng thái của đơn hàng (PENDING, COMPLETED, CANCELLED)
     */
    private String status;

    /**
     * Tổng giá trị của đơn hàng
     */
    private Double totalPrice;

    /**
     * Người dùng đặt đơn hàng này
     * Quan hệ nhiều-một với bảng User
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Danh sách các món trong đơn hàng
     * Quan hệ một-nhiều với bảng OrderItem, cascade để đảm bảo các thao tác được áp dụng cho cả items
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}
