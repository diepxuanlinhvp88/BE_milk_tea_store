package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class chính của ứng dụng Spring Boot cho Hệ thống Quản lý đặt đồ uống Trà sữa.
 * Class này đóng vai trò là điểm khởi đầu cho ứng dụng và khởi tạo Spring context.
 */
@SpringBootApplication
public class DemoApplication {
    // Khai báo logger cho class DemoApplication
    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    /**
     * Phương thức main để khởi động ứng dụng Spring Boot
     * @param args Các tham số dòng lệnh được truyền vào ứng dụng
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        LOG.info("Ứng dụng bắt đầu chạy");
    }
}
