package com.poly.lab7.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab7.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{}