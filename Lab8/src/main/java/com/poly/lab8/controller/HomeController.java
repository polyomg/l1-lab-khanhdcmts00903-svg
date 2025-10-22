package com.poly.lab8.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/") // Ánh xạ tới URL gốc
    public String home() {
        // Chuyển hướng đến trang đăng nhập (do Bài 4/5 quan trọng)
        return "redirect:/auth/login"; 
        
        // HOẶC chuyển hướng đến trang gửi mail (do Bài 3 đã hoàn thành)
        // return "redirect:/mail"; 
        
        // HOẶC bạn có thể trả về một view (ví dụ: "home.html")
        // return "home";
    }
}