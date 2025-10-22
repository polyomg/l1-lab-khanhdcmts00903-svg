package com.poly.lab3.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffController {

    @GetMapping("/staff/detail")
    public String detail(Model model) {
        Staff staff = Staff.builder()
                .id("user@gmail.com")
                .fullname("Nguyen Van Uer")
                .gender(true)
                .level(3)
                .salary(5924.68)
                .build();

        model.addAttribute("staff", staff);
        return "staff-detail";
    }
    
    @GetMapping("/staff/status")
    public String status(Model model) {
        List<Staff> list = Arrays.asList(
                Staff.builder().id("user1@gmail.com").fullname("Nguyen Van User1").build(),
                Staff.builder().id("user2@gmail.com").fullname("Nguyen Van User2").build(),
                Staff.builder().id("user3@gmail.com").fullname("Nguyen Van User3").build(),
                Staff.builder().id("user4@gmail.com").fullname("Nguyen Van User4").build(),
                Staff.builder().id("user5@gmail.com").fullname("Nguyen Van User5").build()
        );
        model.addAttribute("list", list);
        return "list-status";
    }
    
    @GetMapping("/staff/controls")
    public String controls(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("user1@gmail.com").fullname("Nguyễn Văn User1").level(1).build(),
                Staff.builder().id("user2@gmail.com").fullname("Nguyễn Văn User2").level(2).build(),
                Staff.builder().id("user3@gmail.com").fullname("Nguyễn Văn User3").level(3).build(),
                Staff.builder().id("user4@gmail.com").fullname("Nguyễn Văn User4").level(1).build(),
                Staff.builder().id("user5@gmail.com").fullname("Nguyễn Văn User5").level(2).build(),
                Staff.builder().id("user6@gmail.com").fullname("Nguyễn Văn User6").level(3).build()
        );

        model.addAttribute("list", list);
        return "list-controls";
    }


}
