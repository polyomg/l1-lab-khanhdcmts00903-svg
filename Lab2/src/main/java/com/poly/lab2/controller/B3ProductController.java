package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class B3ProductController {

    @GetMapping("/product/form")
    public String form(Product product) {
        product.setName("Iphone");
        product.setPrice(20.0);
        return "product/form";
    }

    @PostMapping("/product/saveB3")
    public String save(@ModelAttribute Product product, Model model) {
        product.setPrice(product.getPrice() * 2);
        
        return "product/form";
    }
}
