package com.poly.lab7.entity;

import java.io.Serializable;

// Đây là một Interface Projection, không phải Entity
public interface Report {
    // Tên phương thức phải khớp với bí danh (alias) trong JPQL
    
    // Ánh xạ đến o.category AS group (Category)
    Serializable getGroup();// Tên cột: Loại hàng [cite: 574, 549]
    
    // Ánh xạ đến sum(o.price) AS sum (Double)
    Double getSum();// Tên cột: Tổng giá [cite: 575, 549]
    
    // Ánh xạ đến count(o) AS count (Long)
    Long getCount();// Tên cột: Số sản phẩm [cite: 576, 549]
}