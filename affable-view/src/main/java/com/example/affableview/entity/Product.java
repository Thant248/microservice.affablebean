package com.example.affableview.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

@AllArgsConstructor
public class Product {


    private int id;

    private String name;
    private double price;
    private List<Integer> quantityList = new ArrayList<>();
    private String description;
    private int quantity;



    private LocalDateTime lastUpdate;

    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public Product() {
    }

    public Product(String name, double price, String description, LocalDateTime lastUpdate, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.category = category;
    }
}
