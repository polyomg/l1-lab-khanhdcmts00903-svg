package com.poly.lab8.dao;

import com.poly.lab8.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, String> {
    // JpaRepository cung cấp sẵn findById(username)
}