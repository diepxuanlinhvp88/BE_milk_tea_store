package com.example.demo.service;

import com.example.demo.dto.request.OrderItemRequest;
import com.example.demo.dto.respone.OrderItemResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.Topping;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ToppingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation của OrderItemService interface.
 * Xử lý các nghiệp vụ liên quan đến chi tiết đơn hàng trong hệ thống.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    // Repository để thao tác với dữ liệu chi tiết đơn hàng
    @Autowired
    OrderItemRepository orderItemRepository;

    // Repository để thao tác với dữ liệu sản phẩm
    @Autowired
    ProductRepository productRepository;

    // Repository để thao tác với dữ liệu topping
    @Autowired
    ToppingRepository toppingRepository;

    // Mapper để chuyển đổi giữa OrderItem entity và DTO
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderItem addOrderItem(OrderItemRequest request) {
        // Tìm sản phẩm theo ID
        Product product = productRepository.findById(request.productId()).orElse(null);
        
        // Lấy danh sách topping theo các ID được chọn
        List<Topping> toppings = toppingRepository.findAllById(request.toppingIds());

        // Tạo mới đối tượng OrderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setToppings(toppings);
        orderItem.setQuantity(request.quantity());

        // Tính tổng giá và thiết lập cho order item
        double price = calculateTotalPrice(request.quantity(), toppings, product);
        orderItem.setPrice(price);

        return orderItem;
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        // Tìm chi tiết đơn hàng theo ID và chuyển đổi sang response
        OrderItem orderItem = orderItemRepository.findById(id).orElse(null);
        return orderItemMapper.toOrderItemResponse(orderItem);
    }

    @Override
    public void deleteOrderItemById(Long id) {
        // Xóa chi tiết đơn hàng theo ID
        orderItemRepository.deleteById(id);
    }

    /**
     * Tính tổng giá của một món trong đơn hàng
     * @param quantity Số lượng sản phẩm
     * @param toppings Danh sách topping được chọn
     * @param product Sản phẩm được đặt
     * @return Tổng giá của món (bao gồm giá sản phẩm và topping)
     */
    double calculateTotalPrice(int quantity, List<Topping> toppings, Product product) {
        double totalPrice = 0;
        // Tính giá sản phẩm theo số lượng
        totalPrice = product.getPrice() * quantity;
        
        // Cộng thêm giá các topping theo số lượng
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice() * quantity;
        }
        return totalPrice;
    }
}
