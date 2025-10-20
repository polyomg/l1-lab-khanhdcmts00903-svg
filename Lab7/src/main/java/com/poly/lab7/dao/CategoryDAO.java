package com.poly.lab7.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab7.entity.Category;

// T là Category, ID là String
public interface CategoryDAO extends JpaRepository<Category, String>{}