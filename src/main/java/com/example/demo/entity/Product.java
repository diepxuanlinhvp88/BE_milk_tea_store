package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product đại diện cho 1 sản phẩm trong cửa hàng, VD: tra dao, tra sua....
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private double price;


}
