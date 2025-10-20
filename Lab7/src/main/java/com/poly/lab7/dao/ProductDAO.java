package com.poly.lab7.dao;

import java.util.List;

// ❌ LỖI: Xóa import java.awt.print.Pageable;
// ✅ SỬA: Thêm import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // SỬA LỖI NÀY
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import com.poly.lab7.entity.Product;
import com.poly.lab7.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer>{

    // Bài 1 - Tìm kiếm theo khoảng giá
    //@Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2") 
	//List<Product> findByPrice(double minPrice, double maxPrice);
	
	
	// Bài 4 - Thay thế phương thức @Query cũ bằng phương thức DSL
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    
    // LAB 7 - Bài 2: Tìm kiếm theo Keywords (JPQL) và Phân trang
    // Pageable đã sử dụng đúng lớp Spring Data
   // @Query("FROM Product o WHERE o.name LIKE ?1")
   // Page<Product> findByKeywords(String keywords, Pageable pageable);
    
 // LAB 7 - Bài 3: Truy vấn tổng hợp Tồn kho theo Loại hàng
    @Query("SELECT o.category AS group, sum(o.price) AS sum, count(o) AS count "
         + "FROM Product o "
         + "GROUP BY o.category "
         + "ORDER BY sum(o.price) DESC")
    List<Report> getInventoryByCategory();
    
    //LAB 7 - Bài 5: Tìm kiếm theo Keywords và Phân trang sử dụng DSL
    // Cú pháp DSL: NameLike tương đương với WHERE name LIKE ?1
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}