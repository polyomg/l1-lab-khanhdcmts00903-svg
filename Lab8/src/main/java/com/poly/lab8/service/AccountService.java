package com.poly.lab8.service;

import com.poly.lab8.model.Account;

public interface AccountService {
    Account findById(String username);
}