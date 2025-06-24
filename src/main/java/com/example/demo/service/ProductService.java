package com.example.demo.service;

import com.example.demo.dto.request.ProductCreateRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.respone.ProductCreateResponse;
import com.example.demo.dto.respone.ProductResponse;
import com.example.demo.entity.Product;

public interface ProductService {
    ProductCreateResponse addProduct(ProductCreateRequest request);
    ProductResponse updateProduct(Long id,ProductUpdateRequest productUpdateRequest);
    void deleteProduct(Long id);
    Product getProductById(Long id);
}
