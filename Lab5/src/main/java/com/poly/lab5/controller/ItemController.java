package com.poly.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.lab5.DB;
import com.poly.lab5.service.ShoppingCartService;

@Controller
public class ItemController {

    @Autowired
    ShoppingCartService cart; // Tiêm ShoppingCartService

    @RequestMapping("/item/index")
    public String list(Model model) {
        // 1. Lấy danh sách sản phẩm (như cũ)
        model.addAttribute("items", DB.items.values());
        
        // 2. Thêm thông tin Giỏ hàng vào Model (Mới)
        model.addAttribute("cart", cart); // Thêm đối tượng giỏ hàng vào Model
        model.addAttribute("totalAmount", cart.getAmount()); // Hoặc thêm trực tiếp tổng tiền
        model.addAttribute("totalCount", cart.getCount()); // Thêm tổng số lượng
        
        return "item/index";
    }
}