package com.example.affableview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

public class Category {


    private int id;

    private String name;


    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public void addProducts(Product product){
       product.setCategory(this);
       products.add(product);
    }
}
