package com.poly.lab5.service;

import java.util.Collection;

import com.poly.lab5.model.Item;

public interface ShoppingCartService {

    /** Thêm mặt hàng vào giỏ hoặc tăng số lượng lên 1 nếu đã tồn tại [cite: 187, 190] */
    Item add(Integer id);

    /** Xóa mặt hàng khỏi giỏ [cite: 192, 195] */
    void remove(Integer id);

    /** Thay đổi số lượng lên của mặt hàng trong giỏ [cite: 197, 202] */
    Item update(Integer id, int qty);

    /** Xóa sạch các mặt hàng trong giỏ [cite: 204, 206] */
    void clear();

    /** Lấy tất cả các mặt hàng trong giỏ [cite: 208, 210] */
    Collection<Item> getItems();

    /** Lấy tổng số lượng các mặt hàng trong giỏ [cite: 212, 214] */
    int getCount();

    /** Lấy tổng số tiền tất cả các mặt hàng trong giỏ [cite: 216, 223] */
    double getAmount();
}