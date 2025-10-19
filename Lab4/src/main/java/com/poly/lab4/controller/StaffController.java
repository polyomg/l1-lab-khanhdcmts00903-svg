package com.poly.lab4.controller;

import com.poly.lab4.model.Staff;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff-validate";
    }

    @PostMapping("/create/save")
    public String createSave(
            @RequestParam("photo_file") MultipartFile file,
            @Valid @ModelAttribute("staff") Staff staff,
            Errors errors,
            Model model) throws IOException {

        if (file != null && !file.isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            String fileName = file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));
            staff.setPhoto(fileName);
        }

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi bên dưới!");
        } else {
            model.addAttribute("message", "Dữ liệu đã nhập đúng!");
        }

        return "staff-validate";
    }
}
