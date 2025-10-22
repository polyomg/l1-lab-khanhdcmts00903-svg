package com.poly.lab5.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.lab5.service.CookieService;
import com.poly.lab5.service.ParamService;
import com.poly.lab5.service.SessionService;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService; 

    @Autowired
    ParamService paramService;   

    @Autowired
    SessionService sessionService; 

    // Xử lý GET: Hiển thị form đăng nhập
    @GetMapping("/account/login")
    public String login1() {
        return "/account/login"; 
    }
    // Xử lý POST: Đọc tham số, kiểm tra đăng nhập và xử lý cookie/session
    @PostMapping("/account/login")
    public String login2() {
        // 1. Đọc các tham số từ request bằng ParamService
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);
        // 2. Kiểm tra đăng nhập thành công (un="poly", pw="123")
        if (un.equals("poly") && pw.equals("123")) {
            // Lưu username vào session
            sessionService.set("username", un);
            // Xử lý ghi nhớ tài khoản
            if (rm) {
                // Ghi nhớ tài khoản 10 ngày
                cookieService.add("user", un, 10 * 24); 
            } else {
                // Xóa cookie tài khoản
                cookieService.remove("user");
            }
            return "redirect:/account/login?success=true"; 
        }

        // Đăng nhập thất bại
        return "/account/login";
    }
    // === BÀI 3: ĐĂNG KÝ VÀ UPLOAD HÌNH ===

    // Xử lý GET: Hiển thị form đăng ký
    @GetMapping("/account/register")
    public String register1() {
        return "/account/register"; // Trả về tên view: /account/register.html
    }

    // Xử lý POST: Đọc tham số và lưu file
    @PostMapping("/account/register")
    public String register2(Model model, 
                            @RequestParam("photo") MultipartFile file) { // Tham số MultipartFile để nhận file

        try {
            // 1. Đọc các tham số text bằng ParamService
            String fullname = paramService.getString("fullname", "N/A");
            String username = paramService.getString("username", "N/A");

            // 2. Lưu file upload bằng ParamService.save()
            // path là thư mục con trong thư mục webapp/resources
            File savedFile = paramService.save(file, "/images/");

            // 3. Ghi kết quả vào Model để hiển thị
            model.addAttribute("message", "Đăng ký thành công!");
            model.addAttribute("fullname", fullname);
            model.addAttribute("username", username);
            model.addAttribute("filename", savedFile != null ? savedFile.getName() : "Không có file được upload");
            model.addAttribute("filePath", savedFile != null ? "/images/" + savedFile.getName() : "N/A");

        } catch (RuntimeException e) {
            model.addAttribute("message", "Đăng ký thất bại: " + e.getMessage());
        }
        
        return "/account/register";
    }
}