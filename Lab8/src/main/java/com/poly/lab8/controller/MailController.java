package com.poly.lab8.controller;

import com.poly.lab8.service.MailService;
import com.poly.lab8.service.MailService.Mail; // Import class Mail từ interface MailService

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailController {

    @Autowired
    MailService mailService;
    
    // 1. Phương thức hiển thị Form Gửi Mail
    @GetMapping("/mail")
    public String mailForm() {
        return "sendMailForm"; // Trả về tên file HTML (sendMailForm.html)
    }

    // 2. Phương thức xử lý Form
    @PostMapping("/mail/process")
    public String processMail(
            Model model,
            // Các tham số form
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam(value = "cc", required = false) String cc,
            @RequestParam(value = "bcc", required = false) String bcc,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam(value = "filenames", required = false) String filenames,
            @RequestParam("method") String method // Lựa chọn phương thức gửi
    ) {
        // 1. Tạo đối tượng Mail từ dữ liệu form
        Mail mail = Mail.builder()
                        .from(from).to(to).cc(cc).bcc(bcc)
                        .subject(subject).body(body).filenames(filenames)
                        .build();

        try {
            String resultMessage;
            if (method.equals("send")) {
                // Gửi Trực tiếp (Bài 1)
                mailService.send(mail);
                resultMessage = "Mail đã được gửi đi TRỰC TIẾP thành công!";
            } else { // method.equals("push")
                // Xếp vào Hàng đợi (Bài 2)
                mailService.push(mail);
                resultMessage = "Mail đã được XẾP VÀO HÀNG ĐỢI thành công!";
            }
            
            model.addAttribute("message", resultMessage);
        } catch (Exception e) {
            // Xử lý lỗi (ví dụ: lỗi xác thực SMTP, file đính kèm không tồn tại)
            model.addAttribute("message", "Gửi mail thất bại: " + e.getMessage());
            model.addAttribute("error", true); // Đánh dấu là lỗi để style màu đỏ
            e.printStackTrace();
        }

        return "sendMailForm"; // Quay lại form với thông báo kết quả
    }
    
    // Giữ lại endpoint /mail/send của Bài 2 để kiểm tra nhanh (tùy chọn)
    /*
    @ResponseBody
    @RequestMapping("/mail/send")
    public String send(Model model) { 
        mailService.push("receiver@gmail.com", "Subject", "Body");
        return "Mail của bạn đã được xếp vào hàng đợi"; 
    }
    */
}