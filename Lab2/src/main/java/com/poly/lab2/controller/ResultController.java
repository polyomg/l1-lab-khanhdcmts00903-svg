package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/a")
public class ResultController {

    @GetMapping("")
    public String m1() {
        return "poly/a"; // (Có thể là "poly/a" nếu a.html nằm trong thư mục poly)
    }

    @GetMapping("/b")
    public String m2(Model model) {
        model.addAttribute("message", "I come from b");
        return "poly/a";
    }

    @GetMapping("/c")
    public String m3(RedirectAttributes params) {
        params.addAttribute("message", "I come from c");
        return "redirect:/a"; // ?2: Sửa lỗi, phải redirect về URL "/a"
    }

    @GetMapping("/d")
    @ResponseBody
    public String m4() {
        return "I come from d";
    }
}