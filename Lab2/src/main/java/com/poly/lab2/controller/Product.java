package com.poly.lab2.controller;

public class Product {

    private String name;
    private double price; // primitive double

    // Constructor mặc định
    public Product() {
    }

    // Constructor đầy đủ
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
