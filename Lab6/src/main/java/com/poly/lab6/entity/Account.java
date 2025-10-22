package com.poly.lab6.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
    @Id
    String username; // Khóa chính
    String password;
    String fullname;
    String email;
    String photo;
    boolean activated;
    boolean admin;
    
    // Quan hệ 1-n với Order
    @OneToMany(mappedBy = "account")
    List<Order> orders;
}