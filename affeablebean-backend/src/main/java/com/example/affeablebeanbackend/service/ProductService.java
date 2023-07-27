package com.example.affeablebeanbackend.service;

import com.example.affeablebeanbackend.dao.CategoryDao;
import com.example.affeablebeanbackend.dao.ProductDao;
import com.example.affeablebeanbackend.entity.Categories;
import com.example.affeablebeanbackend.entity.Product;
import com.example.affeablebeanbackend.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final CategoryDao categoryDao;




    public Products listAllProduct(){
        return new Products(productDao.findAll().spliterator());
    }

    public Categories listAllCategory(){
        return new Categories(categoryDao.findAll().spliterator());
    }
}
