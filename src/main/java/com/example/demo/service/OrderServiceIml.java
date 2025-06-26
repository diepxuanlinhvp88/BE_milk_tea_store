package com.example.demo.service;

import com.example.demo.dto.request.OrderCreateRequest;
import com.example.demo.dto.request.OrderItemRequest;
import com.example.demo.dto.respone.OrderResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation cho việc quản lý đơn hàng.
 * Class này xử lý tất cả các nghiệp vụ liên quan đến đơn hàng như tạo mới, cập nhật, hủy và xóa đơn hàng.
 */
@Service
public class OrderServiceIml implements OrderService {
    // Logger để ghi log cho class OrderServiceIml
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceIml.class);

    // Các dependency cần thiết được inject
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderMapper orderMapper;

    // Các trạng thái có thể có của đơn hàng
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    /**
     * Tạo mới một đơn hàng từ thông tin trong request
     * @param orderCreateRequest Thông tin đơn hàng cần tạo
     * @return OrderResponse chứa thông tin đơn hàng đã được tạo
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderResponse createOrder(OrderCreateRequest orderCreateRequest) {
        LOG.info("Bắt đầu tạo đơn hàng cho khách hàng ID: {}", orderCreateRequest.customerId());
        try {
            // Tìm thông tin khách hàng
            User user = userRepository.findById(orderCreateRequest.customerId()).orElseThrow();
            
            // Khởi tạo đơn hàng mới
            Order order = new Order();
            order.setUser(user);
            order.setCreatedAt(LocalDateTime.now());
            order.setStatus(STATUS_PENDING);
            List<OrderItem> orderItems = new ArrayList<>();
            double totalPrice = 0;

            // Xử lý từng sản phẩm trong đơn hàng
            LOG.debug("Xử lý {} sản phẩm trong đơn hàng", orderCreateRequest.items().size());
            for (OrderItemRequest orderItemRequest : orderCreateRequest.items()) {
                OrderItem orderItem = orderItemService.addOrderItem(orderItemRequest);
                orderItem.setOrder(order);
                orderItems.add(orderItem);
                totalPrice += orderItem.getPrice();
            }
            order.setOrderItems(orderItems);
            order.setTotalPrice(totalPrice);
            order.setStatus(STATUS_COMPLETED);
            
            // Lưu đơn hàng và chi tiết đơn hàng
            LOG.info("Lưu đơn hàng với tổng giá trị: {}", totalPrice);
            Order savedOrder = orderRepository.save(order);
            orderItemRepository.saveAll(orderItems);

            LOG.info("Đã tạo thành công đơn hàng ID: {}", savedOrder.getId());
            return orderMapper.toOrderResponse(savedOrder);
        } catch (Exception e) {
            LOG.error("Lỗi khi tạo đơn hàng: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create order: " + e.getMessage(), e);
        }
    }

    /**
     * Lấy thông tin một đơn hàng theo ID
     * @param orderId ID của đơn hàng cần tìm
     * @return OrderResponse chứa thông tin đơn hàng
     */
    @Override
    public OrderResponse getOrder(Long orderId) {
        LOG.debug("Tìm kiếm đơn hàng với ID: {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow();
        return orderMapper.toOrderResponse(order);
    }

    /**
     * Lấy danh sách đơn hàng của một khách hàng
     * @param userId ID của khách hàng
     * @return Danh sách các đơn hàng của khách hàng
     */
    @Override
    public List<OrderResponse> getOrdersbyUserId(Long userId) {
        LOG.debug("Lấy danh sách đơn hàng của khách hàng ID: {}", userId);
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders.stream().map(orderMapper::toOrderResponse).collect(Collectors.toList());
    }

    /**
     * Hủy một đơn hàng
     * @param orderId ID của đơn hàng cần hủy
     */
    @Override
    public void cancelOrder(Long orderId) {
        LOG.info("Bắt đầu hủy đơn hàng ID: {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(STATUS_CANCELLED);
        orderRepository.save(order);
        LOG.info("Đã hủy thành công đơn hàng ID: {}", orderId);
    }

    /**
     * Xóa một đơn hàng và tất cả chi tiết đơn hàng liên quan
     * @param orderId ID của đơn hàng cần xóa
     */
    @Override
    public void deleteOrder(Long orderId) {
        LOG.info("Bắt đầu xóa đơn hàng ID: {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow();
        List<OrderItem> orderItems = order.getOrderItems();
        orderItemRepository.deleteAll(orderItems);
        orderRepository.delete(order);
        LOG.info("Đã xóa thành công đơn hàng ID: {} và {} sản phẩm liên quan", orderId, orderItems.size());
    }

    /**
     * Lấy danh sách tất cả các đơn hàng trong hệ thống
     * @return Danh sách tất cả các đơn hàng
     */
    @Override
    public List<OrderResponse> getAllOrders() {
        LOG.debug("Lấy danh sách tất cả đơn hàng");
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(orderMapper.toOrderResponse(order));
        }
        LOG.debug("Tìm thấy {} đơn hàng", orders.size());
        return orderResponses;
    }
}
