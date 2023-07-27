package com.example.affeablebeanbackend.controller;

import com.example.affeablebeanbackend.dao.ProductDao;
import com.example.affeablebeanbackend.entity.Product;
import com.example.affeablebeanbackend.entity.Products;
import com.example.affeablebeanbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/backend")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductDao productDao;




    @GetMapping("/products")
    public Products findAllProduct(Model model){
        return productService.listAllProduct();
    }

    @GetMapping("/products/all")
    public Iterable<Product> products(){
        return productDao.findAll();
    }
}
