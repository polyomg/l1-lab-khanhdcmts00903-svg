package com.poly.lab8.service;

import com.poly.lab8.model.Account;
import com.poly.lab8.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account findById(String username) {
        // findById trả về Optional, cần dùng .orElse(null) hoặc .get()
        return accountDAO.findById(username).orElse(null);
    }
}