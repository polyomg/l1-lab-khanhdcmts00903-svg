package com.poly.lab6.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; // Khóa chính, tự tăng
    Double price;
    Integer quantity;
    
    // Quan hệ n-1 với Product
    @ManyToOne @JoinColumn(name = "Productid")
    Product product;
    
    // Quan hệ n-1 với Order
    @ManyToOne @JoinColumn(name = "Orderid")
    Order order;
}