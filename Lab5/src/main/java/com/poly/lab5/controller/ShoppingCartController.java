package com.poly.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.lab5.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
    
    @Autowired
    ShoppingCartService cart;
    // 1. Hiển thị giỏ hàng
    @RequestMapping("/cart/view")
    public String view(Model model) {
        model.addAttribute("cart", cart);
        return "cart/index";
    }
    // 2. Thêm mặt hàng vào giỏ
    @RequestMapping("/cart/add/{id}") 
    public String add(@PathVariable("id") Integer id) {
        cart.add(id);
        return "redirect:/cart/view";// hiển thị giỏ hàng 
    }
    // 3. Xóa mặt hàng khỏi giỏ
    @RequestMapping("/cart/remove/{id}") 
    public String remove(@PathVariable("id") Integer id) {
        cart.remove(id);
        return "redirect:/cart/view";
    }
    // 4. Cập nhật số lượng
    @RequestMapping("/cart/update/{id}") 
    public String update(@PathVariable("id") Integer id, 
                         @RequestParam("qty") Integer qty) { // Lấy qty từ form 
        cart.update(id, qty);
        return "redirect:/cart/view";
    }

    // 5. Xóa sạch giỏ hàng
    @RequestMapping("/cart/clear") 
    public String clear() {
        cart.clear(); 
        return "redirect:/cart/view";
    }
}