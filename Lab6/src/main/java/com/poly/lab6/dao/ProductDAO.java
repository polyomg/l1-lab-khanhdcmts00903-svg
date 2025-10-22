package com.poly.lab6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{}