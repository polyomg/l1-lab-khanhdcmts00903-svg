package com.poly.lab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ListController {

    @GetMapping("/staff/list")
    public String list(Model model) {
        List<Staff> list = Arrays.asList(
                Staff.builder().id("user1@gmail.com").fullname("Nguyen Van User1").photo("staff.jpg").salary(512345.68).build(),
                Staff.builder().id("user2@gmail.com").fullname("Nguyen Van User2").photo("staff.jpg").salary(512345.68).build(),
                Staff.builder().id("user3@gmail.com").fullname("Nguyen Van User3").photo("staff.jpg").salary(512345.68).build(),
                Staff.builder().id("user4@gmail.com").fullname("Nguyen Van User4").photo("staff.jpg").salary(512345.68).build(),
                Staff.builder().id("user5@gmail.com").fullname("Nguyen Van User5").photo("staff.jpg").salary(512345.68).build()
        );

        model.addAttribute("list", list);
        return "staff-list";
    }
    
    
}

