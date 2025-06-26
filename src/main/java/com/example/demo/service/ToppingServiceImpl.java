package com.example.demo.service;

import com.example.demo.dto.request.ToppingRequest;
import com.example.demo.dto.respone.ApiResponse;
import com.example.demo.dto.respone.ToppingResponse;
import com.example.demo.entity.Topping;
import com.example.demo.repository.ToppingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation của ToppingService interface.
 * Xử lý các nghiệp vụ liên quan đến topping trong hệ thống.
 */
@Service
@AllArgsConstructor
public class ToppingServiceImpl implements ToppingService {
    // Repository để thao tác với dữ liệu topping
    private ToppingRepository toppingRepository;

    /**
     * Thêm 1 topping mới vào cửa hàng.
     * @param toppingRequest Thông tin topping cần tạo
     * @return thoong tin topping da duoc tuy chon
     */
    @Override
    public ToppingResponse createTopping(ToppingRequest toppingRequest) {
        // Tạo mới đối tượng Topping
        Topping topping = new Topping();
        topping.setName(toppingRequest.name());
        topping.setPrice(toppingRequest.price());
        
        // Lưu topping vào database
        toppingRepository.save(topping);
        
        // Trả về response với thông tin topping đã tạo
        return new ToppingResponse(topping.getId(), topping.getName(), topping.getPrice());
    }

    /**
     * Cap nhat topping
     * @param id ID của topping cần cập nhật
     * @param toppingRequest Thông tin cập nhật của topping
     * @return topping duoc cap nhat
     */
    @Override
    public ToppingResponse updateTopping(Long id, ToppingRequest toppingRequest) {
        // Tìm topping theo ID, throw exception nếu không tìm thấy
        Topping topping = toppingRepository.findById(id).orElseThrow(() -> new RuntimeException("Topping not found"));
        
        // Cập nhật các thuộc tính nếu có trong request
        if (toppingRequest.name() != null) {
            topping.setName(toppingRequest.name());
        }
        if (toppingRequest.price() != 0.0) {
            topping.setPrice(toppingRequest.price());
        }
        
        // Lưu topping đã cập nhật
        toppingRepository.save(topping);
        
        // Trả về response với thông tin topping sau khi cập nhật
        return new ToppingResponse(topping.getId(), topping.getName(), topping.getPrice());
    }

    /**
     * Xóa 1 topping theo ID.
     * Kiểm tra xem topping có đang được sử dụng trong đơn hàng nào không trước khi xóa.
     * @param id ID của topping cần xóa
     * @throws RuntimeException nếu topping đang được sử dụng trong đơn hàng
     */
    @Override
    @Transactional
    public void deleteTopping(Long id) {
        // Tìm topping theo ID
        Topping topping = toppingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy topping với ID: " + id));
        
        // Kiểm tra xem topping có đang được sử dụng trong đơn hàng nào không
        if (topping.getOrderItems() != null && !topping.getOrderItems().isEmpty()) {
            throw new RuntimeException("Không thể xóa topping vì đang được sử dụng trong " + 
                topping.getOrderItems().size() + " đơn hàng");
        }
        
        // Nếu không được sử dụng, thực hiện xóa
        toppingRepository.deleteById(id);
    }

    /**
     * Lấy toàn bộ toppings có trong hệ thống.
     * @return toàn bo toppings
     */
    @Override
    public List<ToppingResponse> getAllToppings() {
        // Khởi tạo danh sách response
        List<ToppingResponse> toppingResponses = new ArrayList<>();
        
        // Lấy tất cả topping và chuyển đổi sang ToppingResponse
        toppingRepository.findAll().forEach(topping -> {
            toppingResponses.add(new ToppingResponse(topping.getId(), topping.getName(), topping.getPrice()));
        });
        
        return toppingResponses;
    }

    /**
     * Lấy topping theo ID.
     * @param id ID của topping cần tìm
     * @return
     */
    @Override
    public ToppingResponse getTopping(Long id) {
        // Tìm topping theo ID và chuyển đổi sang ToppingResponse
        Topping topping = toppingRepository.findById(id).orElseThrow(() -> new RuntimeException("Topping not found"));
        return new ToppingResponse(topping.getId(), topping.getName(), topping.getPrice());
    }
}
