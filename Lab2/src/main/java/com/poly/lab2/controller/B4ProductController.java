package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class B4ProductController {

    private List<Product> products = new ArrayList<>();
    private final Product p1 = new Product("Iphone 13", 150000); // p1 cố định

    public B4ProductController() {
        products.add(new Product("A", 1.0));
        products.add(new Product("B", 2.0));
    }

    @GetMapping("/product/formB4")
    public String form(Model model) {
        model.addAttribute("p1", p1);
        model.addAttribute("product", new Product());
        model.addAttribute("products", products);
        return "product/formB4";
    }

    @PostMapping("/product/saveB4")
    public String save(@ModelAttribute("product") Product product, Model model) {
        products.add(product);

        model.addAttribute("p1", p1);
        model.addAttribute("p2", product); // sản phẩm vừa nhập
        model.addAttribute("products", products);
        return "product/formB4";
    }
}
