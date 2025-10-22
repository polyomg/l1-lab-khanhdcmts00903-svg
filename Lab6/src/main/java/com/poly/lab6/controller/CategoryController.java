package com.poly.lab6.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.poly.lab6.dao.CategoryDAO;
import com.poly.lab6.entity.Category;

@Controller
public class CategoryController {

    @Autowired
    CategoryDAO dao; // làm việc với bảng Categories

    // 1. READ (Hiển thị danh sách và form rỗng)
    @RequestMapping("/category/index")
    public String index(Model model) {
        Category item = new Category();
        model.addAttribute("item", item); // item: buộc lên form
        List<Category> items = dao.findAll();
        model.addAttribute("items", items); // items: hiển thị lên bảng
        return "category/index";
    }

    // 2. EDIT (Tải dữ liệu Category lên form)
    @RequestMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        // Lấy Category theo ID để hiển thị lên form
        Optional<Category> optItem = dao.findById(id);
        Category item = optItem.orElse(new Category()); // Nếu không tìm thấy, tạo mới
        model.addAttribute("item", item);

        // Tải lại danh sách items để hiển thị bảng
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    // 3. CREATE (Thêm mới)
    @RequestMapping("/category/create")
    public String create(Category item) {
        dao.save(item); // Spring Data JPA tự hiểu là CREATE nếu ID không tồn tại
        return "redirect:/category/index";
    }
    
    // 4. UPDATE (Cập nhật)
    @RequestMapping("/category/update")
    public String update(Category item) {
        dao.save(item); // Spring Data JPA tự hiểu là UPDATE nếu ID đã tồn tại
        return "redirect:/category/edit/" + item.getId();
    }
    
    // 5. DELETE (Xóa)
    @RequestMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        dao.deleteById(id);
        return "redirect:/category/index";
    }
}