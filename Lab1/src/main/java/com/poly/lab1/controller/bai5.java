
package com.poly.lab1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class bai5 {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/bai5/form")
    public String form() {
        return "poly/bai5";
    }

    @PostMapping("/rectangle/calc")
    public String calc(Model model) {
        double chieuDai = Double.parseDouble(request.getParameter("chieudai"));
        double chieuRong = Double.parseDouble(request.getParameter("chieurong"));

        double dienTich = chieuDai * chieuRong;
        double chuVi = 2 * (chieuDai + chieuRong);

        model.addAttribute("chieudai", chieuDai);
        model.addAttribute("chieurong", chieuRong);

        model.addAttribute("dientich", dienTich);
        model.addAttribute("chuvi", chuVi);

        String msg = "Diện tích = " + chieuDai + " * " + chieuRong + " = " + dienTich
                   + " ; Chu vi = 2 * (" + chieuDai + " + " + chieuRong + ") = " + chuVi;
        model.addAttribute("message", msg);

        return "poly/bai5";
    }
}
