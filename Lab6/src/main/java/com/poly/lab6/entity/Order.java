package com.poly.lab6.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; // Khóa chính, tự tăng
    String address;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    Date createDate = new Date(); // Mặc định là ngày hiện tại
    
    // Quan hệ n-1 với Account
    @ManyToOne @JoinColumn(name = "Username")
    Account account;
    
    // Quan hệ 1-n với OrderDetail
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}