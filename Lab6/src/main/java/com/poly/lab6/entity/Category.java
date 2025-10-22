package com.poly.lab6.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

 
@Entity
@Table(name = "Categories")
public class Category implements Serializable{
    @Id
    String id;
    String name;
    
    // Quan hệ 1-n với Product
    @OneToMany(mappedBy = "category") 
    List<Product> products;
}