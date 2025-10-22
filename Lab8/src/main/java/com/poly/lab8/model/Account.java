package com.poly.lab8.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Accounts")
@Data // Dùng Lombok để tự sinh getter/setter/toString/etc.
public class Account {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String photo;
    private Boolean activated;
    private Boolean admin; // Dùng cho Bài 5

    // Phương thức kiểm tra vai trò admin, cần thiết cho AuthInterceptor (Bài 5)
    public boolean isAdmin() {
        return this.admin != null && this.admin;
    }
}