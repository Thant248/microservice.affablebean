package com.example.affeablebeanbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "product")
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private String description;


    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

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
