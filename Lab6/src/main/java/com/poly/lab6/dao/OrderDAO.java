package com.poly.lab6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{}