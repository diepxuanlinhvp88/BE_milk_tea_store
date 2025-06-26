package com.example.demo.service;

import com.example.demo.dto.request.ProductCreateRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.respone.ProductCreateResponse;
import com.example.demo.dto.respone.ProductResponse;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation của ProductService interface.
 * Xử lý các nghiệp vụ liên quan đến sản phẩm trong hệ thống.
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    // Repository để thao tác với dữ liệu sản phẩm
    ProductRepository productRepository;

    @Override
    public ProductCreateResponse addProduct(ProductCreateRequest productCreateRequest) {
        // Tạo mới đối tượng Product
        Product product = new Product();

        // Thiết lập các thuộc tính từ request
        product.setName(productCreateRequest.name());
        product.setDescription(productCreateRequest.description());
        product.setPrice(productCreateRequest.price());
        productRepository.save(product);

        // Trả về response với thông tin cơ bản của sản phẩm đã tạo
        return new ProductCreateResponse(product.getId(), product.getName(), product.getPrice());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {
        // Tìm sản phẩm theo ID, throw exception nếu không tìm thấy
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        
        // Cập nhật các thuộc tính nếu có trong request
        if (productUpdateRequest.name() != null) {
            product.setName(productUpdateRequest.name());
        }
        if (productUpdateRequest.description() != null) {
            product.setDescription(productUpdateRequest.description());
        }
        if (productUpdateRequest.price() != 0.0) {
            product.setPrice(productUpdateRequest.price());
        }
        
        // Lưu sản phẩm đã cập nhật
        productRepository.save(product);
        
        // Trả về response với thông tin đầy đủ của sản phẩm
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    @Override
    public void deleteProduct(Long id) {
        // Xóa sản phẩm theo ID
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        // Tìm và trả về sản phẩm theo ID, throw exception nếu không tìm thấy
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
