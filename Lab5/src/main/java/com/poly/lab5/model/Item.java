package com.poly.lab5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    Integer id;     // Mã mặt hàng [cite: 229]
    String name;    // Tên mặt hàng [cite: 230]
    double price;   // Giá [cite: 231]
    int qty = 1;    // Số lượng, mặc định là 1 [cite: 232]
}