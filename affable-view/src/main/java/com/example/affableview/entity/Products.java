package com.example.affableview.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@NoArgsConstructor
public class Products {

    private List<Product> products = new ArrayList<>();

    public Products(Spliterator<Product> iterable){
        products = StreamSupport.stream(iterable,false).collect(Collectors.toList());
    }
}
