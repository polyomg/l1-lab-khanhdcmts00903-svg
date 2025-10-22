package com.poly.lab5.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.lab5.model.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SessionScope // Đảm bảo bean được tạo theo từng phiên làm việc [cite: 240]
@Service // Khai báo là Spring Bean Service [cite: 241]
public class ShoppingCartServiceImpl implements ShoppingCartService {

    // Map để chứa các mặt hàng trong giỏ: Key là ID, Value là Item [cite: 238, 243]
    private Map<Integer, Item> map = new HashMap<>();

    // === HÀM GIẢ LẬP DB (Sẽ được thay thế bằng lớp DB của Bài 5) ===
    // Dùng để lấy thông tin Item khi thêm vào giỏ lần đầu
    private Map<Integer, Item> itemsDB = new HashMap<>();
    
    // Khởi tạo dữ liệu giả lập để add được item [cite: 299-305]
    public ShoppingCartServiceImpl() {
        itemsDB.put(1, new Item(1, "Samsung", 10.0, 0));
        itemsDB.put(2, new Item(2, "Nokia 2021", 20.50, 0));
        itemsDB.put(3, new Item(3, "iPhone 20", 90.49, 0));
        itemsDB.put(4, new Item(4, "Motorola", 15.55, 0));
        itemsDB.put(5, new Item(5, "Seamen", 8.99, 0));
    }
    // ===============================================================

    @Override
    public Item add(Integer id) {
        Item item = map.get(id);

        if (item != null) {
            // Nếu mặt hàng đã tồn tại, tăng số lượng lên 1 [cite: 187]
            item.setQty(item.getQty() + 1);
        } else {
            // Nếu mặt hàng chưa tồn tại, thêm mới với số lượng mặc định là 1
            Item newItem = itemsDB.get(id); // Lấy chi tiết mặt hàng từ DB giả lập
            if (newItem != null) {
                item = new Item(newItem.getId(), newItem.getName(), newItem.getPrice(), 1); // Đặt qty = 1
                map.put(id, item);
            }
        }
        return item; // Trả về mặt hàng đã được thêm hoặc cập nhật [cite: 189]
    }

    @Override
    public void remove(Integer id) {
        map.remove(id); // Xóa mặt hàng khỏi Map [cite: 192, 195]
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);

        if (item != null) {
            // Cập nhật số lượng mới [cite: 197]
            item.setQty(qty); 
            
            // Xóa khỏi giỏ nếu số lượng <= 0
            if (item.getQty() <= 0) {
                remove(id);
                return null;
            }
        }
        return item; // Trả về mặt hàng đã được thay đổi số lượng [cite: 200]
    }

    @Override
    public void clear() {
        map.clear(); // Xóa sạch Map [cite: 204]
    }

    @Override
    public Collection<Item> getItems() {
        return map.values(); // Trả về tất cả các mặt hàng trong giỏ [cite: 208, 210]
    }

    @Override
    public int getCount() {
        // Tính tổng số lượng của tất cả mặt hàng trong giỏ [cite: 212]
        return map.values().stream()
                .mapToInt(Item::getQty)
                .sum();
    }

    @Override
    public double getAmount() {
        // Tính tổng số tiền của tất cả mặt hàng trong giỏ [cite: 216, 223]
        return map.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
    }
}