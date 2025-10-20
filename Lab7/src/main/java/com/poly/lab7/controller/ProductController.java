package com.poly.lab7.controller;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.lab7.dao.ProductDAO;
import com.poly.lab7.entity.Product;
import com.poly.lab7.service.SessionService; // ⬅️ Cần import SessionService

@Controller
@RequestMapping("/product") // Đường dẫn gốc là /product
public class ProductController {

    @Autowired
    ProductDAO dao; // làm việc với bảng Products
    
    @Autowired
    SessionService session; // Dịch vụ quản lý Session

    // LAB 6 - Bài 3: Sắp xếp (Sorting)
    // URL: /product/sort?field=...
    @RequestMapping("/sort")
    public String sort(Model model, 
                       @RequestParam("field") Optional<String> field) {
        
        // Sắp xếp GIẢM DẦN (Direction.DESC) theo cột đã chọn, mặc định là "price" [cite: 972]
        Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
        
        // Truy vấn tất cả sản phẩm, có áp dụng sắp xếp [cite: 974]
        List<Product> items = dao.findAll(sort);
        model.addAttribute("items", items);
        
        model.addAttribute("field", field.orElse("price").toUpperCase());
        
        return "product/sort";
    }

    // LAB 6 - Bài 4: Phân trang (Pagination)
    // URL: /product/page?p=...
    @RequestMapping("/page")
    public String paginate(Model model,
                           @RequestParam("p") Optional<Integer> p) {
        
       // Khởi tạo Pageable: p.orElse(0) là số trang (mặc định là 0), 5 là kích thước trang [cite: 1046]
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        
     // Truy vấn dữ liệu, trả về đối tượng Page<Product> [cite: 1046]
        Page<Product> page = dao.findAll(pageable);
        
        model.addAttribute("page", page);
        
        return "product/page";
    }
    
    // LAB 7 - Bài 1: Tìm kiếm theo khoảng giá (@Query JPQL)
    // URL: /product/search?min=...&max=...
    @RequestMapping("/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min, 
                         @RequestParam("max") Optional<Double> max) {
        
        // Xác định giá trị mặc định cho min và max
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        
        // Gọi phương thức tìm kiếm theo khoảng giá
        //List<Product> items = dao.findByPrice(minPrice, maxPrice); - Bai 1
        // Bài 4
        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);	
        
        return "product/search";
    }
    
    // LAB 7 - Bài 2: Tìm kiếm theo tên và Phân trang (Kết hợp Session)
    // URL: /product/search-and-page?keywords=...&p=...
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw, 
                                @RequestParam("p") Optional<Integer> p) { 
        
        // 1. Xử lý Keywords: Ưu tiên từ Request (kw), nếu rỗng thì lấy từ Session
        String kwords = kw.orElse(session.get("keywords", "")); 
        session.set("keywords", kwords); // Ghi nhớ từ khóa vào Session
        
        // 2. Cấu hình Phân trang (5 sản phẩm/trang)
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        
        // 3. Truy vấn dữ liệu: Thêm "%" để thực hiện LIKE
        //Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);
        
       //bai 5
        Page<Product> page = dao.findAllByNameLike("%" + kwords + "%", pageable);
        
        // 4. Thêm dữ liệu vào Model
        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords); // Thêm keywords vào model để hiển thị trên form
        
        return "product/search-and-page";
    }
}