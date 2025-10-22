package com.poly.lab6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{}