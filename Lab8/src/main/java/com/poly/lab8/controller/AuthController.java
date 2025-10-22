package com.poly.lab8.controller;

import com.poly.lab8.model.Account;
import com.poly.lab8.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    // Hiển thị form đăng nhập
    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        return "/auth/login";
    }

    // Xử lý quá trình đăng nhập
    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        // Tìm kiếm tài khoản theo username
        Account user = accountService.findById(username);
        
        // Logic đăng nhập
        if (user == null) { // Sai username
            model.addAttribute("message", "Invalid email!");
        } else if (!user.getPassword().equals(password)) { // Sai password
            model.addAttribute("message", "Invalid password!");
        } else { // Đăng nhập thành công
            session.setAttribute("user", user);
            model.addAttribute("message", "Login successfully!");
            
            // Chuyển hướng đến trang chủ (sẽ được nâng cấp ở Bài 5)
            return "redirect:/";
        }
        
        // Quay lại trang login nếu thất bại
        return "/auth/login";
    }
}