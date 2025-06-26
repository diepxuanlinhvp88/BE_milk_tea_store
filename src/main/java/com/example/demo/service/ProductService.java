package com.example.demo.service;

import com.example.demo.dto.request.ProductCreateRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.respone.ProductCreateResponse;
import com.example.demo.dto.respone.ProductResponse;
import com.example.demo.entity.Product;

/**
 * Interface định nghĩa các phương thức xử lý nghiệp vụ liên quan đến sản phẩm.
 * Cung cấp các thao tác CRUD cho sản phẩm trong hệ thống.
 */
public interface ProductService {

    /**
     * Thêm một sản phẩm mới vào hệ thống
     * @param request Thông tin sản phẩm cần thêm
     * @return ProductCreateResponse chứa thông tin sản phẩm đã được tạo
     */
    ProductCreateResponse addProduct(ProductCreateRequest request);

    /**
     * Cập nhật thông tin của một sản phẩm
     * @param id ID của sản phẩm cần cập nhật
     * @param productUpdateRequest Thông tin cập nhật của sản phẩm
     * @return ProductResponse chứa thông tin sản phẩm sau khi cập nhật
     */
    ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest);

    /**
     * Xóa một sản phẩm khỏi hệ thống
     * @param id ID của sản phẩm cần xóa
     */
    void deleteProduct(Long id);

    /**
     * Lấy thông tin của một sản phẩm theo ID
     * @param id ID của sản phẩm cần tìm
     * @return Product entity chứa thông tin sản phẩm
     */
    Product getProductById(Long id);
}
