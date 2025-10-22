package com.poly.lab7.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.lab7.dao.ProductDAO;
import com.poly.lab7.entity.Report; // Cần import Report interface

@Controller
public class ReportController {
    
    // Sử dụng ProductDAO để truy vấn dữ liệu tổng hợp
    @Autowired
    ProductDAO dao;

    // LAB 7 - Bài 3: Báo cáo Tồn kho theo Loại hàng
    // URL: /report/inventory-by-category
    @RequestMapping("/report/inventory-by-category")
    public String inventory(Model model) {
        
        // Gọi phương thức truy vấn tổng hợp
        List<Report> items = dao.getInventoryByCategory();
        model.addAttribute("items", items);
        
        return "report/inventory-by-category";
    }
}