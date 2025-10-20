package com.poly.lab7.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab7.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{}